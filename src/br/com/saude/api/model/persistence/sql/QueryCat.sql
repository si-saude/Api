select 
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia, 
	c.rta, 
	EXTRACT(MONTH from c.dataocorrencia) as mesocorrencia, 
	EXTRACT(DOW from c.dataocorrencia) as diasemanaocorrencia,
	i.nome as nomeinstalacao, 
	e.vinculo, 
	es.nome as nomeempresa, 
	cnae.codigo as codigocnae, 
	c.grauriscoempresa, 
	p.nome as nomeempregado, 
	r.nome as regime, 
	ghe.duracaojornada, 
	ca.geraafastamento,
	pca.descricao as partecorpoatingida, 
	COALESCE(extract(hour from c.dataocorrencia)||':'||extract(minute from c.dataocorrencia)) as horaocorrencia,
	cid.nome as cid, 
	c.numerosisin, 
	c.classificacaosisin,
	get_day_month(EXTRACT(DAY from c.dataocorrencia)) || '/' || get_day_month(EXTRACT(MONTH from c.dataocorrencia)) || '/' || EXTRACT(YEAR from c.dataocorrencia) as dataocorrencia,
	get_day_month(EXTRACT(DAY from c.dataemissao)) || '/' || get_day_month(EXTRACT(MONTH from c.dataemissao)) || '/' || EXTRACT(YEAR from c.dataemissao) as dataemissao,
	c.dataocorrencia as datadataocorrencia, 
	c.dataemissao as datadataemissao,
	cg.titulo as classificacaogravidade, 
	get_day_month(EXTRACT(DAY from c.dataavaliacaomedica)) || '/' || get_day_month(EXTRACT(MONTH from c.dataavaliacaomedica)) || '/' || EXTRACT(YEAR from c.dataavaliacaomedica) as dataavaliacaomedica,
	c.registrosd2000, 
	c.catsd2000,
	(select t.inicio from tarefa t inner join servico s on t.servico_id = s.id where s.grupo = 'ATENDIMENTO OCUPACIONAL' and s.codigo = '0004' and t.status = 'CONCLUÍDA' and t.cliente_id = e.id and t.inicio > c.dataocorrencia order by t.inicio limit 1) as dataretornotrabalho, 
	c.pendenciacorrecao,
	c.justificativaatrasoemissaocat, 
	c.numerocartamulta, 
	c.tipoacidente, 
	c.tipocat, 
	COALESCE(dp.codigo||' - '||dp.descricao) as diagnosticoprovavel,
	ac.descricao as agentecausador, 
	c.comunicavelsus, 
	c.ferimentograve, 
	c.numerocat, 
	pca.descricao as partecorpoatingida2, 
	nl.descricao as naturezalesao, 
	c.codigocartasindicato, 
	c.classificacaoanomalia,
	get_day_month(EXTRACT(DAY from c.datacomunicacaosindicato)) || '/' || get_day_month(EXTRACT(MONTH from c.datacomunicacaosindicato)) || '/' || EXTRACT(YEAR from c.datacomunicacaosindicato) as datacomunicacaosindicato,
	c.justificativaatrasoemissaocarta,
	get_day_month(EXTRACT(DAY from p.datanascimento)) || '/' || get_day_month(EXTRACT(MONTH from p.datanascimento)) || '/' || EXTRACT(YEAR from p.datanascimento) as datanascimento,
	p.sexo as sexo, 
	e.escolaridade, 
	e.estadocivil, 
	c.remuneracao, 
	cargo.nome as cargo, 
	c.dataavaliacaomedica as datadataavaliacaomedica, 
	p.datanascimento as datadatanascimento,
	c.tempoprevisto, 
	c.datacomunicacaosindicato as datadatacomunicacaosindicato,
	c.ato1,
	c.ato2,
	c.ato3,
	c.ato4
	from cat c
		inner join gerencia g4 on c.gerencia_id = g4.id
		left join gerencia g3 on g4.gerencia_id = g3.id
		left join gerencia g2 on g3.gerencia_id = g2.id
		left join gerencia g1 on g2.gerencia_id = g1.id
		left join instalacao i on i.id = c.instalacao_id
		inner join empregado e on e.id = c.empregado_id
		inner join pessoa p on p.id = e.pessoa_id
		left join ghe ghe on ghe.id = e.ghe_id
		left join regime r on r.id = e.regime_id
		left join empresa es on es.id = c.empresa_id
		left join cargo cargo on cargo.id = e.cargo_id
		left join cnae cnae on cnae.id = c.cnae_id
		left join partecorpoatingida pca on pca.id = c.partecorpoatingida_id
		left join naturezalesao nl on nl.id = c.naturezalesao_id
		left join cidade cid on cid.id = c.municipio_id
		left join classificacaogravidade cg on cg.id = c.classificacaogravidade_id
		left join diagnostico dp on dp.id = c.diagnosticoprovavel_id
		left join agentecausador ac on ac.id = c.agentecausador_id
		left join classificacaoafastamento ca on c.classificacao_id = ca.id