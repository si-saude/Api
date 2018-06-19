select id,gerencia,matricula,nome,mes_convocacao,base,
	EXTRACT(DAY from aso_anterior) || '/' || EXTRACT(MONTH from aso_anterior) || '/' || EXTRACT(YEAR from aso_anterior) as aso_anterior1,
	EXTRACT(DAY from realizacao_pre_clinico) || '/' || EXTRACT(MONTH from realizacao_pre_clinico) || '/' || EXTRACT(YEAR from realizacao_pre_clinico) as realizacao_pre_clinico1,
	EXTRACT(DAY from aso_atual) || '/' || EXTRACT(MONTH from aso_atual) || '/' || EXTRACT(YEAR from aso_atual) as aso_atual1,
	situacao_aso,
	CASE situacao_aso
	WHEN 'PERIÓDICO REALIZADO'
	THEN CASE WHEN date_trunc('day', aso_atual) <= date_trunc('day', aso_anterior_validade)
		  THEN 'DENTRO DO PRAZO' ELSE 'FORA DO PRAZO' END
	ELSE '' END as aso_no_prazo,
	pendencias,	
	primeira_linha,
	CASE WHEN exames_pendentes IS NULL THEN 'REALIZADO'
	ELSE 'PENDENTE' END as status_pre_clinico,
	exames_pendentes

from
	(select *,
		CASE 
		WHEN status_empregado = 'AFASTADO' OR status_empregado = 'SINDICALISTA' THEN status_empregado
		WHEN existe_periodico = 0 and aso_valido = 0 THEN 'PERIÓDICO NÃO REALIZADO (ASO INVÁLIDO)'
		WHEN existe_periodico = 0 and aso_valido = 1 THEN 'PERIÓDICO NÃO REALIZADO (ASO VÁLIDO)'
		WHEN existe_periodico = 1 THEN 'PERIÓDICO REALIZADO' ELSE '' END as situacao_aso
		
	from(
		select e.id,COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
			e.matricula, p.nome,  
			e.status as status_empregado,
			CASE date_part('month', c.inicio)
				WHEN 1 THEN 'JANEIRO'
				WHEN 2 THEN 'FEVEREIRO'
				WHEN 3 THEN 'MARÇO'
				WHEN 4 THEN 'ABRIL'
				WHEN 5 THEN 'MAIO'
				WHEN 6 THEN 'JUNHO'
				WHEN 7 THEN 'JULHO'
				WHEN 8 THEN 'AGOSTO'
				WHEN 9 THEN 'SETEMBRO'
				WHEN 10 THEN 'OUTUBRO'
				WHEN 11 THEN 'NOVEMBRO'
				WHEN 12 THEN 'DEZEMBRO' 
				ELSE '' END as mes_convocacao,
			b.nome as base,
			a0.data as aso_anterior,
			a0.validade as aso_anterior_validade,
			re.data as realizacao_pre_clinico,
			a1.data as aso_atual,
			a1.validade as aso_atual_validade,
			CASE (select COALESCE(sum(1),0) 
				from atendimento a
				inner join tarefa t on a.tarefa_id = t.id
				inner join servico s on t.servico_id = s.id
				 where a.aso_id = a1.id
				   and s.codigo = '0003' and s.grupo = 'ATENDIMENTO OCUPACIONAL'
				   and t.status = 'CONCLUÍDA')
			WHEN 0 THEN 0
			ELSE 1 END existe_periodico,

			CASE 
			WHEN COALESCE(a1.validade,a0.validade) >= now() THEN 1 ELSE 0 END aso_valido,

			CASE
			WHEN a1.validade IS NOT NULL AND a0.validade IS NOT NULL
			THEN 1 ELSE 0 END existe_data_aso,

			(select string_agg(eq.nome, ' - ')
			from atendimento a
			inner join filaesperaocupacional f on a.filaesperaocupacional_id = f.id
			inner join tarefa t on t.id = a.tarefa_id
			inner join tarefa ta on t.cliente_id = ta.cliente_id
			inner join equipe eq on ta.equipe_id = eq.id
			where a.aso_id = a1.id and ta.status = 'ABERTA' and date_trunc('day', t.inicio) = date_trunc('day', ta.inicio) ) as pendencias,
			
			(select string_agg(ex.descricao, ' - ')
			from empregadoconvocacaoexame x
			inner join exame ex on x.exame_id = ex.id
			where x.empregadoconvocacao_id = ec.id
			  and x.realizacao is null) as exames_pendentes,
			
			COALESCE(g1.codigo||'/'||g2.codigo,g2.codigo||'/'||g3.codigo,g3.codigo||'/'||g4.codigo) as primeira_linha,
			ec.auditado
		from empregado e
		inner join pessoa p on e.pessoa_id = p.id
		inner join gerencia g4 on e.gerencia_id = g4.id
		left join gerencia g3 on g4.gerencia_id = g3.id
		left join gerencia g2 on g3.gerencia_id = g2.id
		left join gerencia g1 on g2.gerencia_id = g1.id
		left join empregadoconvocacao ec on ec.id = (select max(ecc.id) from empregadoconvocacao ecc
							     where ecc.empregado_id = e.id)
						     and exists (select 1 from convocacao cc where cc.id = ec.convocacao_id
								 and date_trunc('year', cc.inicio) = date_trunc('year', now())
								 and cc.tipo = 'PERIÓDICO' )
		left join convocacao c on ec.convocacao_id = c.id
		inner join base b on e.base_id = b.id
		left join aso a0 on a0.id = (select max(a.id) from aso a where a.empregado_id = e.id 
						and date_trunc('year', a.data) < date_trunc('year', now()))
		left join aso a1 on a1.id = (select max(a.id) from aso a where a.empregado_id = e.id 
						and date_trunc('year', a.data) = date_trunc('year', now()))
		left join resultadoexame re on re.id = (select max(r.id) from resultadoexame r where r.empregadoconvocacao_id = ec.id )
		where e.vinculo = 'PRÓPRIO') p) panorama