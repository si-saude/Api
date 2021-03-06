SELECT rp.id as id, get_valor_risco_potencial( rp.id ) as valor,
	p.nome as empregadoNome, eq.nome as equipeResponsavelNome,
	get_day_month(EXTRACT(DAY from rp.data)) || '/' || get_day_month(EXTRACT(MONTH from rp.data)) || '/' || EXTRACT(YEAR from rp.data) as data,
	rp.status as status, rp.abreviacaoequipeacolhimento as abreviacaoequipeacolhimento,
	get_pendencia_encerramento_risco_potencial_equipe(rp.id, [PROFISSIONAL_ID]) as pendenciaEncerramento,
	get_pendencia_validacao_risco_potencial(rp.id) as pendenciaValidacao,
	get_pendencia_reavaliacao_risco_potencial_equipe(rp.id, [PROFISSIONAL_ID]) as pendenciaReavaliacao
from riscopotencial rp 
	inner join empregado e on rp.empregado_id = e.id
	inner join base b on e.base_id = b.id
	inner join pessoa p on e.pessoa_id = p.id
	left join equipe eq on rp.equiperesponsavel_id = eq.id
where atual = 't' and b.uf = [BASE_UF]