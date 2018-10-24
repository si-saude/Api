select COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
	p.nome as nome,
	e.matricula as matricula,
	get_valor_risco_potencial(rp.id) as statusrpsat,
	rp.status as statusRisco,
	ea.nome as equipe,
	ei.abreviacao||' - '||i.codigo||' - '||i.nome|| ' - ' ||t.indice  as indicador,
	d.descricao as diagnostico,
	int.descricao as intervencao,
	t.id as idtriagem,
	a.id as idacao,
	ai.descricao as acao,
	a.tipo as tipoAcao,
	a.tipoContato as contato,
	a.status as statusAcao,
	ac.descricao as acompanhamento
from riscopotencial rp
inner join empregado e on e.id = rp.empregado_id
inner join pessoa p on p.id = e.pessoa_id
inner join gerencia g4 on e.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
inner join riscoempregado re on rp.id = re.riscopotencial_id
inner join triagem t on re.id = t.riscoempregado_id
left join diagnostico d on t.diagnostico_id = d.id
left join intervencao int on int.id = t.intervencao_id
inner join equipe ea on ea.id = t.equipeabordagem_id
inner join indicadorsast i on t.indicadorsast_id = i.id
inner join equipe ei on ei.id = i.equipe_id
left join acao a on t.id = a.triagem_id
left join acaointervencao ai on a.acaointervencao_id = ai.id
left join acompanhamento ac on ac.acao_id = a.id,
profissional pro
where exists (select 1 from riscopotencial_equipe rr
		where rr.riscopotencial_id = rp.id
		  and rr.equipe_id = t.equipeabordagem_id)
  and rp.abreviacaoequipeacolhimento = [ABREVIACAO_EQUIPE_ACOLHIMENTO]
  and pro.id = [ID_PROFISSIONAL]
  and EXTRACT(YEAR FROM rp.data) = [ANO_RISCO]
  and (pro.equipe_id = t.equipeabordagem_id OR pro.equipe_id = rp.equiperesponsavel_id)
order by 1,p.nome,ea.nome,7,ai.descricao,ac.descricao