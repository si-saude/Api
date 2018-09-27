select p.nome, em.matricula,
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia,
	b.nome as nomebase,
	a.inicio as inicioatestado,
	addnumerodiasinicioatestado(a.id) as fimatestado,
	a.numerodias as numerodias,
	a.datasolicitacao as datarecebimento,
	get_day_month(EXTRACT(MONTH from a.datasolicitacao)) as mesrecebimento,
	t.inicio as dataentrega,
	t.fim as datahomologacao,
	a.limitehomologar as limitehomologar,
	get_day_month(EXTRACT(MONTH from t.fim)) as meshomologacao,
	eq.abreviacao as abreviacaoequipe,
	substring(trim(p_e.nome) FROM '^([^ ]+)') as nomeprofissionaltarefa,
	a.atestadofisicorecebido,
	a.observacao,
	a.status,
	ag.inicio
from atestado a
left join empregado em on a.empregado_id = em.id
inner join gerencia g4 on em.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
inner join base b on em.base_id = b.id
inner join pessoa p on p.id = em.pessoa_id
left join tarefa t on t.id = a.tarefa_id
left join tarefa ag on ag.id = a.agendamento_id
left join equipe eq on eq.id = t.equipe_id
left join profissional pr on pr.id = t.responsavel_id
left join empregado e_pr on e_pr.id = pr.empregado_id
left join pessoa p_e on p_e.id = e_pr.pessoa_id
where a.datasolicitacao between [DATA_INICIO] and [DATA_FIM] or a.inicio between [DATA_INICIO] and [DATA_FIM]