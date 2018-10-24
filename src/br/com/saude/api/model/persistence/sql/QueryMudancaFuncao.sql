select 
	(select inicio from mudancafuncao_tarefa mft 
		inner join tarefa t1 on t1.id = mft.tarefa_id
		inner join equipe eq1 on t1.equipe_id = eq1.id
			where mft.mudancafuncao_id = mf.id and eq1.abreviacao = 'HIG') as datainiciotarefahigiente,
	p.nome, 
	e.matricula,
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerenciaatual, 
	COALESCE( g21.codigo||'/','')||COALESCE( g22.codigo||'/','')||COALESCE( g23.codigo||'/','')||g24.codigo as gerenciafutura,
	ghea.nome as gheatual,
	ghef.nome as ghefuturo,
	gheea.nome as gheeatual,
	gheef.nome as gheefuturo,
	funcaoa.nome as funcaoatual,
	funcaof.nome as funcaofuturo,
	regimea.nome as regimeatual,
	regimef.nome as regimefuturo,
	basea.nome as baseatual,
	basef.nome as basefuturo,
	enfasea.descricao as enfaseatual,
	enfasef.descricao as enfasefuturo,
	(select status from mudancafuncao_tarefa mft 
		inner join tarefa t1 on t1.id = mft.tarefa_id
		inner join equipe eq1 on t1.equipe_id = eq1.id
			where mft.mudancafuncao_id = mf.id and eq1.abreviacao = 'HIG') as statustarefahigiene,
	(select status from mudancafuncao_tarefa mft 
		inner join tarefa t1 on t1.id = mft.tarefa_id
		inner join equipe eq1 on t1.equipe_id = eq1.id
			where mft.mudancafuncao_id = mf.id and eq1.abreviacao = 'ERG') as statustarefaergonomia,
	(select status from mudancafuncao_tarefa mft 
		inner join tarefa t1 on t1.id = mft.tarefa_id
		inner join equipe eq1 on t1.equipe_id = eq1.id
			where mft.mudancafuncao_id = mf.id and eq1.abreviacao = 'MED') as statustarefamedicina,
	mf.atividades
		from mudancafuncao mf
			inner join empregado e on e.id = 
				(select e1.id from mudancafuncao_tarefa mft 
					inner join tarefa t1 on t1.id = mft.tarefa_id
					inner join equipe eq1 on t1.equipe_id = eq1.id
					inner join empregado e1 on e1.id = t1.cliente_id
						where mft.mudancafuncao_id = mf.id and eq1.abreviacao = 'HIG')
			inner join pessoa p on p.id = e.pessoa_id
			inner join gerencia g4 on e.gerencia_id = g4.id
			left join gerencia g3 on g4.gerencia_id = g3.id
			left join gerencia g2 on g3.gerencia_id = g2.id
			left join gerencia g1 on g2.gerencia_id = g1.id
			inner join gerencia g24 on mf.gerencia_id = g24.id
			left join gerencia g23 on g4.gerencia_id = g23.id
			left join gerencia g22 on g3.gerencia_id = g22.id
			left join gerencia g21 on g2.gerencia_id = g21.id
			left join ghe ghea on e.ghe_id = ghea.id
			left join ghe ghef on mf.ghe_id = ghef.id
			left join ghee gheea on e.ghee_id = gheea.id
			left join ghee gheef on mf.ghee_id = gheef.id
			left join regime regimea on e.regime_id = regimea.id
			left join regime regimef on mf.regime_id = regimef.id
			left join base basea on e.base_id = basea.id
			left join base basef on mf.base_id = basef.id
			left join enfase enfasea on e.enfase_id = enfasea.id
			left join enfase enfasef on mf.enfase_id = enfasef.id
			left join funcao funcaoa on e.funcao_id = funcaoa.id
			left join funcao funcaof on mf.funcao_id = funcaof.id