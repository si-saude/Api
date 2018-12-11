select p.nome, em.matricula,
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
	b.nome as nomebase,
	a.conforme as conforme,
	a.data as datarealizacao,
	a.validade as datavalidade
from aso as a
left join empregado em on a.empregado_id = em.id
inner join gerencia g4 on em.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
inner join base b on em.base_id = b.id
inner join pessoa p on p.id = em.pessoa_id
where datarealizacao between [DATA_INICIO] and [DATA_FIM] or datavalidade between [DATA_INICIO] and [DATA_FIM]