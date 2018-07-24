select p.nome, a.cid, 
	EXTRACT(DAY from t.inicio) || '/' || EXTRACT(MONTH from t.inicio) || '/' || EXTRACT(YEAR from t.inicio) as inicio, 
	a.impossibilidadeLocomocao,
	a.status, a.lancadoSap, a.atestadoFisicoRecebido, a.controleLicenca, 
	EXTRACT(DAY from a.dataSolicitacao) || '/' || EXTRACT(MONTH from a.dataSolicitacao) || '/' || EXTRACT(YEAR from a.dataSolicitacao) as dataSolicitacao,
	EXTRACT(DAY from a.dataAgendamento) || '/' || EXTRACT(MONTH from a.dataAgendamento) || '/' || EXTRACT(YEAR from a.dataAgendamento) as dataAgendamento, 
	c.numero, a.numeroDias
from atestado a
inner join tarefa t on t.id = a.tarefa_id
inner join empregado e on e.id = t.cliente_id
inner join pessoa p on p.id = e.pessoa_id
inner join cat c on c.id = a.cat_id