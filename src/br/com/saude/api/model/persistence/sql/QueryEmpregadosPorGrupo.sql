SELECT trim(p.nome) as nome,trim(e.chave) as chave, trim(e.matricula) as matricula,
	COALESCE( g1.codigo||'/','')||COALESCE( g2.codigo||'/','')||COALESCE( g3.codigo||'/','')||g4.codigo as gerencia
FROM empregado e
inner join pessoa p on e.pessoa_id = p.id
left join gerencia g4 on e.gerencia_id = g4.id
left join gerencia g3 on g4.gerencia_id = g3.id
left join gerencia g2 on g3.gerencia_id = g2.id
left join gerencia g1 on g2.gerencia_id = g1.id
where exists (select 1 from grupomonitoramento_empregado ge
		where ge.empregado_id = e.id
		  and ge.grupomonitoramento_id = [GRUPOMONITORAMENTO_ID])
order by 1,4