SELECT sci.id as id, ts.nome as nome, sci.descricao as descricao, 
	get_day_month(EXTRACT(DAY from sci.prazo)) || '/' || get_day_month(EXTRACT(MONTH from sci.prazo)) || '/' || EXTRACT(YEAR from sci.prazo) as prazo, 
	get_day_month(EXTRACT(DAY from sci.abertura)) || '/' || get_day_month(EXTRACT(MONTH from sci.abertura)) || '/' || EXTRACT(YEAR from sci.abertura) as abertura,
	sci.prazo < CURRENT_DATE as atrasado,
	sci.status as status, sci.observacao as observacao, sci.concluido as concluido
FROM solicitacaocentralintegra sci
inner join tiposolicitacao ts on sci.tiposolicitacao_id = ts.id
inner join tarefa t on sci.tarefa_id = t.id
inner join empregado e on t.cliente_id = e.id
inner join pessoa p on e.pessoa_id = p.id
where p.cpf = [CLIENTE_CPF]