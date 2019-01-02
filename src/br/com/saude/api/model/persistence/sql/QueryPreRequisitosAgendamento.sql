select *,
	CASE WHEN coalesce(tab.exame_ids,'') != ''
	THEN 'PENDENTE' ELSE 'REALIZADO' END as status_preclinico,

	CASE WHEN coalesce(tab.exame_ids,'') != ''
	THEN (select string_agg(distinct x.realizacao||'', ' - ')
		from empregadoconvocacaoexame x
		where x.id in (select CAST(regexp_split_to_table(tab.exame_ids, ',') AS INTEGER))
		  and x.realizacao is not null )
	ELSE null END as datas_preclinico,

	CASE WHEN coalesce(tab.exame_ids,'') != ''
	THEN (select string_agg(distinct ex.descricao, '  /  ')
		from empregadoconvocacaoexame x
		inner join exame ex on x.exame_id = ex.id
		where x.id in (select CAST(regexp_split_to_table(tab.exame_ids, ',') AS INTEGER))
		  and x.realizacao is null )
	ELSE null END as exames_pendentes
	
from 	(select trim(e.matricula) as matricula, 
		trim(e.chave) as chave, 
		trim(p.nome) as nome,
		COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
		b.nome as base,
		(select string_agg(x.id||'', ',')
		 from empregadoconvocacaoexame x
		 where x.empregadoconvocacao_id = ec.id
		 and exists (select 1 
				from grupomonitoramento_empregado ge
				inner join grupomonitoramentoexame gx on ge.grupomonitoramento_id = gx.grupomonitoramento_id
				where ge.empregado_id = e.id
				  and gx.exame_id = x.exame_id
				  and COALESCE( gx.opcional,'f') = 'f')) as exame_ids,
		ec.resultadoAuditado as resultadoAuditado,
		(select 1 
		 from tarefa t
		 inner join servico s on t.servico_id = s.id
		 where t.cliente_id = e.id 
		   and s.grupo = 'ATENDIMENTO OCUPACIONAL'
		   and s.codigo = '0003'
		   and t.inicio >= ec.dataconvocacao limit 1) as agendado,
		c.titulo as titulo
		
	from empregadoconvocacao ec
	inner join empregado e on ec.empregado_id = e.id
	inner join pessoa p on e.pessoa_id = p.id
	left join base b on e.base_id = b.id
	inner join gerencia g4 on e.gerencia_id = g4.id
	left join gerencia g3 on g4.gerencia_id = g3.id
	left join gerencia g2 on g3.gerencia_id = g2.id
	left join gerencia g1 on g2.gerencia_id = g1.id
	inner join convocacao c on ec.convocacao_id = c.id
	inner join profissiograma pr on pr.id = c.profissiograma_id
	where c.inicio >= date_trunc('year', now())) as tab
