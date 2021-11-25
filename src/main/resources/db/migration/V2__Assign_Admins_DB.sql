
INSERT INTO public.users VALUES (1, 'admin_romanko03', 'Roman', 'gx901tirtx', '2003-01-09 00:00:00', 'kovalchuk.roman03@gmail.com', '+380986378007', '4daf1e48-b2d3-4171-aa75-5db754b5b4e4.photo_2021-10-13_00-12-56.jpg', 'A1');
INSERT INTO public.users VALUES (2, 'admin_dudarko', 'Serozha', 'meizum234', '2003-03-21 00:00:00', 'meizum2103@gmail.com', '+380983707218', '0457f04f-7e4a-4b46-98ad-b32254666070.photo_2021-06-19_22-49-44.jpg', 'A1');
INSERT INTO public.users VALUES (3, 'Pips', 'Іван', 'mpostryk123', '2003-01-16 00:00:00', 'mypostykr@gmail.com', '+380967766223', 'c0505640-4e89-47f0-8970-b2e5667721b2.photo_2020-04-05_20-25-45.jpg', 'A1');
INSERT INTO public.users VALUES (4, '_vlad', 'vladiuslav', '1234', '2002-10-28 00:00:00', 'vladuisalv@gmail.com', '+380931274257', 'b6d7c208-2d8e-4d15-a35f-1580b6d2bc02.chinchila.jpg', 'A1');


INSERT INTO public.user_role
VALUES (1, 'STUDENT'), (1, 'TEACHER'), (1, 'ADMIN'), (2, 'STUDENT'), (2, 'TEACHER'), (2, 'ADMIN'), (3, 'STUDENT'), (4, 'STUDENT');

ALTER SEQUENCE public.user_id_seq RESTART WITH 5;
