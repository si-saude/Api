select p.nome, a.cid, 
	e.matricula,
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
	b.nome as nomeBase,
	get_day_month(EXTRACT(DAY from t.inicio)) || '/' || get_day_month(EXTRACT(MONTH from t.inicio)) || '/' || EXTRACT(YEAR from t.inicio) as inicio, 
	a.impossibilidadeLocomocao,
	a.status, a.lancadoSap, a.atestadoFisicoRecebido, a.controleLicenca, 
	get_day_month(EXTRACT(DAY from a.dataSolicitacao))|| '/' || get_day_month(EXTRACT(MONTH from a.dataSolicitacao)) || '/' || EXTRACT(YEAR from a.dataSolicitacao) as dataSolicitacao,
	get_day_month(EXTRACT(DAY from a.dataAgendamento)) || '/' || get_day_month(EXTRACT(MONTH from a.dataAgendamento)) || '/' || EXTRACT(YEAR from a.dataAgendamento) as dataAgendamento, 
	c.numero, a.numeroDias
from atestado a
inner join tarefa t on t.id = a.tarefa_id
inner join empregado e on e.id = t.cliente_id
inner join gerencia g4 on e.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
inner join base b on e.base_id = b.id
inner join pessoa p on p.id = e.pessoa_id
inner join cat c on c.id = a.cat_id