create view pedido_finalizado as 
select p.pedido_id , p2.nome_produto , p2.qntd_produto , p2.valor_unitario_produto
from
	pedido p
inner join pedido_produto pp on
	p.pedido_id = pp.pedido_id
inner join produto p2 on
	p2.id_produto  = pp.produto_id 
	
	
select * from pedido_finalizado pf where  = 4