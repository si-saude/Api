select distinct e.id,trim(p.nome),f.status, date_trunc('day', t.inicio), s.nome,
	(select string_agg(e2.abreviacao, ' - ')
			from tarefa t2
			inner join equipe e2 on t2.equipe_id = e2.id
			where t2.status in ('ABERTA','EXECUÇÃO')
			  and t2.cliente_id = e.id
			  and t2.servico_id = t.servico_id
			  and date_trunc('year', t2.inicio) = date_trunc('year', t.inicio)
			) as pendencias
from tarefa t
inner join empregado e on t.cliente_id = e.id
inner join pessoa p on e.pessoa_id = p.id
inner join servico s on t.servico_id = s.id
inner join atendimento a on a.tarefa_id = t.id
inner join filaesperaocupacional f on f.id = a.filaesperaocupacional_id
where date_trunc('day', t.inicio) between [DATA_INICIO_INICIO] and [DATA_INICIO_FIM] and s.id = [SERVICO_ID]
  and s.grupo = 'ATENDIMENTO OCUPACIONAL'

UNION

select distinct e.id,trim(p.nome),t.status, date_trunc('day', t.inicio), s.nome, ''
from tarefa t
inner join empregado e on t.cliente_id = e.id
inner join pessoa p on e.pessoa_id = p.id
inner join servico s on t.servico_id = s.id
where t.inicio between [DATA_INICIO_INICIO] and [DATA_INICIO_FIM] and s.id = [SERVICO_ID]
  and s.grupo = 'ATENDIMENTO OCUPACIONAL'
  and not exists (select 1 
		from atendimento a 
		inner join filaesperaocupacional f on a.filaesperaocupacional_id = f.id
		where f.empregado_id = t.cliente_id
		  and  date_trunc('day', f.horariocheckin) = date_trunc('day', t.inicio) )
order by 2