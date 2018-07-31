select c.numero, c.contratado,c.nome, 
	get_day_month(EXTRACT(DAY from c.dataNascimento)) || '/' || get_day_month(EXTRACT(MONTH from c.dataNascimento)) || '/' || EXTRACT(YEAR from c.dataNascimento) as dataNascimento, 
	c.cargo, c.regime, c.cpf, c.sexo, c.rta, 
	c.instalacao, pca.descricao as pcadescricao, ac.descricao as acdescricao, nl.descricao as nldescricao, 
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia, 
	c.classificacaoSisin, 
	get_day_month(EXTRACT(DAY from c.diaHoraAcidente)) || '/' || get_day_month(EXTRACT(MONTH from c.diaHoraAcidente)) || '/' || EXTRACT(YEAR from c.diaHoraAcidente) as diaHoraAcidente,
	c.afastamento, 
	get_day_month(EXTRACT(DAY from c.dataEmissaoCat)) || '/' || get_day_month(EXTRACT(MONTH from c.dataEmissaoCat)) || '/' || EXTRACT(YEAR from c.dataEmissaoCat) as dataEmissaoCat, 
	c.gravidade, 
	get_day_month(EXTRACT(DAY from c.dataAvaliacaoMedica)) || '/' || get_day_month(EXTRACT(MONTH from c.dataAvaliacaoMedica)) || '/' || EXTRACT(YEAR from c.dataAvaliacaoMedica) as dataAvaliacaoMedica, 
	c.registroSd2000,
	c.catSd2000, c.tipoAcidente, c.tipoCat, d.descricao, c.codigoCartaSindicato, c.comunicavelSus, c.ferimentoGraveConformeAnp,
	get_day_month(EXTRACT(DAY from c.dataComunicacaoSindicato)) || '/' || get_day_month(EXTRACT(MONTH from c.dataComunicacaoSindicato)) || '/' || EXTRACT(YEAR from c.dataComunicacaoSindicato) as dataComunicacaoSindicato, 
	c.remuneracao, f.razaosocial
from cat c
inner join partecorpoatingida pca on pca.id = c.partecorpoatingida_id
inner join agentecausador ac on ac.id = c.agentecausador_id
inner join naturezalesao nl on nl.id = c.naturezalesao_id
inner join gerencia g4 on c.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
left join diagnostico d on d.id = c.diagnostico_id
left join fornecedor f on c.empresa_id = f.id