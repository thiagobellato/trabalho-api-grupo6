create view pedido_finalizado as 
select p.pedido_id , p2.id_produto , p2.qntd_produto , p2.valor_unitario_produto
from
	pedido p
inner join pedido_produto pp on
	p.pedido_id = pp.pedido_id
inner join produto p2 on
	p2.id_produto  = pp.produto_id 
	
	
select * from pedido_finalizado pf where  = 4

select r.name from usuario u
inner join usuario_role ur on u.id_usuario = ur.id_usuario 
inner join roles r on ur.role_id = r.id
where u.email_usuario  = :email