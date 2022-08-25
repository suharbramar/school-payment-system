INSERT INTO public.userrole(role_id,
	role_name, role_description, is_role_enabled)
	VALUES (1, 'ADMIN', 'ADMIN ROLE', 1);
	

INSERT INTO public.applicationuser(
	applicationuser_id, is_accountnonexpired, is_accountnonlocked, is_credentialsnonexpired, is_enabled, password, user_name, role_name)
	VALUES (nextval('application_sequence'), 1, 1, 1, 1, '$2a$10$mN6o1wNN1ZrWnD52/tRXQONC.4jr4f0Dl3qNe.4zyGtKQ3QBGWjqG', 'suharbramar@yahoo.com', 'ADMIN');
	
select * from student; 
select * from ppdb; 

delete from academicyear;