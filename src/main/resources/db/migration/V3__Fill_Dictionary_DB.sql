--
-- PostgreSQL database dump
--

-- Dumped from database version 10.16
-- Dumped by pg_dump version 10.16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: dictionary; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dictionary VALUES (5, 'остерігатися, берегтися', 'to watch out', 'B1', NULL);
INSERT INTO public.dictionary VALUES (7, 'невічливий', 'impolite', 'A1', NULL);
INSERT INTO public.dictionary VALUES (9, 'внесок', 'a contribution', 'B2', NULL);
INSERT INTO public.dictionary VALUES (10, 'понаднормовий', 'overtime', 'B1', NULL);
INSERT INTO public.dictionary VALUES (11, 'прибережний', 'coastal', 'B1', NULL);
INSERT INTO public.dictionary VALUES (12, 'очікувати, предбачати, передчувати', 'to anticipate', 'B1', NULL);
INSERT INTO public.dictionary VALUES (13, 'брати участь', 'to participate', 'B1', NULL);
INSERT INTO public.dictionary VALUES (14, 'щорічний', 'annual', 'A2', NULL);
INSERT INTO public.dictionary VALUES (15, 'детальний', 'thorough', 'A2', NULL);
INSERT INTO public.dictionary VALUES (16, 'табель відпрацьованого часу', 'a timesheet', 'B1', NULL);
INSERT INTO public.dictionary VALUES (17, 'ображати', 'to offend', 'A1', NULL);
INSERT INTO public.dictionary VALUES (18, 'обов''язок', 'a duty', 'A2', NULL);
INSERT INTO public.dictionary VALUES (19, 'складний, жорсткий', 'tough', 'B2', NULL);
INSERT INTO public.dictionary VALUES (20, 'відмовитись (відкинути пропозицію)', 'to waive', 'B1', NULL);
INSERT INTO public.dictionary VALUES (21, 'справлятися з', 'to deal with', 'B1', NULL);
INSERT INTO public.dictionary VALUES (22, 'незначна проблема', 'a slight problem', 'A2', NULL);
INSERT INTO public.dictionary VALUES (23, 'витік', 'a leak', 'B1', NULL);
INSERT INTO public.dictionary VALUES (24, 'щиро кажучи', 'frankly', 'B1', NULL);
INSERT INTO public.dictionary VALUES (26, 'бути на зв''язку', 'to keep in touch', 'A2', NULL);
INSERT INTO public.dictionary VALUES (27, 'утриматися від чогось', 'to keep from', 'B1', NULL);
INSERT INTO public.dictionary VALUES (28, 'утриматися від чогось', 'to abstain from', 'B1', NULL);
INSERT INTO public.dictionary VALUES (29, 'продовжити щось робити', 'to keep at', 'B1', NULL);
INSERT INTO public.dictionary VALUES (30, 'реконструкція', 'redecorating', 'A2', NULL);
INSERT INTO public.dictionary VALUES (32, 'закуска', 'an appetizer', 'A2', NULL);
INSERT INTO public.dictionary VALUES (33, 'намір', 'an intention', 'B1', NULL);
INSERT INTO public.dictionary VALUES (34, 'найманий працівник', 'an employee', 'A1', NULL);
INSERT INTO public.dictionary VALUES (35, 'година', 'an hour', 'A1', NULL);
INSERT INTO public.dictionary VALUES (36, 'зберегти', 'to save', 'A1', NULL);
INSERT INTO public.dictionary VALUES (38, 'акціонер', 'a shareholder', 'B1', NULL);
INSERT INTO public.dictionary VALUES (2, 'образливий', 'offensive', 'B1', NULL);
INSERT INTO public.dictionary VALUES (3, 'ображений', 'offended', 'A1', NULL);
INSERT INTO public.dictionary VALUES (4, 'що можна і що не можна', 'dos and don''s', 'B2', NULL);
INSERT INTO public.dictionary VALUES (31, 'яблуко', 'an apple', 'A1', '02df31c0-44cd-4d6f-a56c-3c09359864c8.apple.jpg');
INSERT INTO public.dictionary VALUES (8, 'підвищення', 'a promoution', 'A1', NULL);
INSERT INTO public.dictionary VALUES (25, 'база даних', 'a database', 'A1', 'c7b931e4-4a6f-45b8-8015-542313cb0e78.db.png');
INSERT INTO public.dictionary VALUES (37, 'відкладати, заощаджувати, зберігати', 'to save for', 'A2', NULL);
INSERT INTO public.dictionary VALUES (39, 'порядок денний', 'an agenda', 'B1', NULL);
INSERT INTO public.dictionary VALUES (40, 'братися за щось', 'to take on', 'A2', NULL);
INSERT INTO public.dictionary VALUES (41, 'облаштування, установка', 'a setup', 'B1', NULL);
INSERT INTO public.dictionary VALUES (42, 'мета, ціль', 'a purpose', 'B1', NULL);
INSERT INTO public.dictionary VALUES (43, 'поставка', 'supply', 'B2', NULL);
INSERT INTO public.dictionary VALUES (44, 'стратегічний', 'strategic', 'A2', NULL);
INSERT INTO public.dictionary VALUES (45, 'робити внесок', 'to contribute', 'B1', NULL);
INSERT INTO public.dictionary VALUES (46, 'призначати, доручати', 'to assign', 'A2', NULL);
INSERT INTO public.dictionary VALUES (47, 'робити помітки (нотатки)', 'to take notes', 'B1', NULL);
INSERT INTO public.dictionary VALUES (48, 'учасник', 'a participant', 'B2', NULL);
INSERT INTO public.dictionary VALUES (49, 'продовжувати(рухатися вперед)', 'to move on', 'A2', NULL);
INSERT INTO public.dictionary VALUES (50, 'на правильному шляху', 'on track', 'B1', NULL);
INSERT INTO public.dictionary VALUES (51, 'запуск продукту', 'a product launch', 'B1', NULL);
INSERT INTO public.dictionary VALUES (52, 'наголошувати, виділяти', 'to emphasize', 'B1', NULL);
INSERT INTO public.dictionary VALUES (53, 'кінцевий споживач', 'an end user', 'B1', NULL);
INSERT INTO public.dictionary VALUES (54, 'виробництво', 'manufacturing', 'B1', NULL);
INSERT INTO public.dictionary VALUES (55, 'прототип', 'a prototype', 'A2', NULL);
INSERT INTO public.dictionary VALUES (56, 'використовувати зв''язки', 'to pull strings', 'B1', NULL);
INSERT INTO public.dictionary VALUES (57, 'налаштування', 'an adjustment', 'B1', NULL);
INSERT INTO public.dictionary VALUES (58, 'переробити', 'to rework', 'B1', NULL);
INSERT INTO public.dictionary VALUES (59, 'перенести(змінити розклад)', 'to reschedule', 'B1', NULL);
INSERT INTO public.dictionary VALUES (60, 'переговори', 'negotiations', 'B1', NULL);
INSERT INTO public.dictionary VALUES (61, 'споживач', 'a consumer', 'A2', NULL);
INSERT INTO public.dictionary VALUES (62, 'архітектура сайту', 'a site architecthure', 'A2', NULL);
INSERT INTO public.dictionary VALUES (63, 'знизувати плечима, знизування', 'shrug', 'B1', NULL);
INSERT INTO public.dictionary VALUES (64, 'життєво необхідний', 'vital', 'B1', NULL);
INSERT INTO public.dictionary VALUES (65, 'всеохоплюючий', 'overwhelming', 'B1', NULL);
INSERT INTO public.dictionary VALUES (67, 'діставати, морочити голову', 'to hassle', 'B1', NULL);
INSERT INTO public.dictionary VALUES (99, 'хмарочос', 'a skyscraper', 'A2', 'fbce090d-1224-4ce2-b73a-437d765fcc5a.1200px-Burj_Khalifa.jpg');
INSERT INTO public.dictionary VALUES (100, 'підкрадатися', 'to creep up', 'B1', 'a8a78198-dfa6-4014-87da-3a85f32a4316.steve-look-behind-you-theres-a-creeper-comin-oh-my-40965277.png');
INSERT INTO public.dictionary VALUES (101, 'винахід', 'invention', 'B1', '38b760d1-6e3f-471e-830d-489edf71b9ea.patreg2_800x0_485.jpeg');
INSERT INTO public.dictionary VALUES (102, 'чудовий, дивовижний', 'marvelous', 'B1', NULL);
INSERT INTO public.dictionary VALUES (68, 'ромашка', 'chamomile', 'B1', 'e48bfa77-34b3-42b3-a3df-03a7e0c1c9e9.chamomile.jpg');
INSERT INTO public.dictionary VALUES (69, 'викривати, наражати', 'to expose', 'B1', NULL);
INSERT INTO public.dictionary VALUES (70, 'покриття', 'coating', 'A2', NULL);
INSERT INTO public.dictionary VALUES (71, 'фонограф', 'a phonograph', 'A2', '711d8af2-0ff4-410f-baf4-3ff5e2d9042e.phonograph.jpg');
INSERT INTO public.dictionary VALUES (72, 'віскі', 'whiskey', 'A2', '022f4341-4c84-4ec6-83f2-ca0f8382e54d.offertaFileFile-155352.jpg');
INSERT INTO public.dictionary VALUES (73, 'шина', 'tire', 'A1', 'cf11693e-84b1-4077-a6e8-e46a36cc9396.tire.jpeg');
INSERT INTO public.dictionary VALUES (74, 'в''язниця, тюрма', 'a prison', 'A1', '17e8abf9-24a6-48a2-a1ff-ee9b03bf5a0d.prison.jpg');
INSERT INTO public.dictionary VALUES (75, 'тюремна камера', 'a jail', 'A2', '0c1a6317-387b-4924-923b-8ab328a94c1b.jail.jpg');
INSERT INTO public.dictionary VALUES (76, 'плем''я, рід', 'tribe', 'B1', '5559d665-fec5-41e4-a0c9-b1e3cb79685d.Tribe.jpg');
INSERT INTO public.dictionary VALUES (77, 'земельна ділянка', 'allotment', 'B2', '8b807a91-9fd0-49eb-944d-80899da53cc2.Allotment-Plot for ict.jpg');
INSERT INTO public.dictionary VALUES (78, 'поступово, повільно', 'gradually', 'B2', NULL);
INSERT INTO public.dictionary VALUES (79, 'через щось, по причині чогось', 'on account of', 'B2', NULL);
INSERT INTO public.dictionary VALUES (80, 'бути свідком, бачити', 'to witness', 'B2', NULL);
INSERT INTO public.dictionary VALUES (81, 'свідок', 'a witness', 'B2', NULL);
INSERT INTO public.dictionary VALUES (82, 'грудна клітина', 'chest', 'B2', '4a7afbfb-c2f7-47b5-aae2-39adabb435e9.chest_noun_002_06536.jpg');
INSERT INTO public.dictionary VALUES (83, 'сльози', 'tears', 'A2', 'ada8cf6b-cf0a-46a9-b9b0-2d02da8cbe83.field_image_tears.jpg');
INSERT INTO public.dictionary VALUES (84, 'справжній, істинний, дійсний', 'genuine', 'B2', 'b432cdd1-d535-4552-babd-5960fc717893.genuine-quality-rubber-stamp-isolated-white-background-91637738.jpg');
INSERT INTO public.dictionary VALUES (85, 'кокос', 'coconut', 'A1', 'd2261343-2174-42fb-af93-ac0061d925e2.qenuine-1400x1400.png');
INSERT INTO public.dictionary VALUES (86, 'сумлінно, старанно', 'industrious', 'B2', NULL);
INSERT INTO public.dictionary VALUES (87, 'заводити годинник', 'to wind a watch', 'B2', '5c6ea168-978d-4ce8-a177-fd763dfdd313.maxresdefault.jpg');
INSERT INTO public.dictionary VALUES (88, 'особливо, особисто, сильно', 'peculiarly', 'B2', NULL);
INSERT INTO public.dictionary VALUES (89, 'сумувати, тужити', 'to yearn', 'B2', NULL);
INSERT INTO public.dictionary VALUES (90, 'радість, веселість', 'gaiety', 'B2', NULL);
INSERT INTO public.dictionary VALUES (91, 'уживана річ', 'hand-me-down', 'B2', '09aa5650-54da-4968-ab33-62faacce84f8.601805c35ae958.57166986.jpg');
INSERT INTO public.dictionary VALUES (92, 'бути винним щось', 'to owe', 'A2', '8e149f5c-995f-4484-927e-c025f4e4a009.borrow-lend-owe-loan-300x200.jpg');
INSERT INTO public.dictionary VALUES (93, 'збиратися, спаковувати речі', 'to pack up', 'B1', '5ce1abcd-5b74-4034-bc8b-87b9b9711b02.image_2021-10-27_22-07-38.png');
INSERT INTO public.dictionary VALUES (94, 'єнот', 'racoon', 'A1', 'c1674410-0e09-4ec1-8550-9e9161185235.7598a8a71105c0d11f3a62c130bca30d.jpg');
INSERT INTO public.dictionary VALUES (95, 'патока, меляса', 'molasess', 'A2', 'e77b0cd8-4f85-4785-b1a1-971d436212b0.molasses-500x500.jpg');
INSERT INTO public.dictionary VALUES (96, 'пара, випар', 'steam', 'B1', 'c042c4d2-92c2-436b-8da4-4862bf51f51a.image_2021-10-27_22-45-52.png');
INSERT INTO public.dictionary VALUES (97, 'пароплав', 'steamer boat', 'B1', 'ee2683f8-1205-49b2-a2dc-cb79d1f1080c.steamb_noun_002_35776.jpg');
INSERT INTO public.dictionary VALUES (98, 'салат лутук', 'lettuce', 'A2', 'c1b0021c-03b9-4bb6-accc-caec01e9e14e.602044a0c42280.86510622.jpg');
INSERT INTO public.dictionary VALUES (103, 'вторгнення', 'invasion', 'B1', '1eab5d22-ec50-4ab2-b188-3102d2c5348b.3a81499efce3afd4ac9544db163e78e09371b27b_hq.jpg');
INSERT INTO public.dictionary VALUES (104, 'сокира', 'an axe', 'A2', 'df7725be-3d05-4e95-aa1b-ab6442461e2c.475-large-carving-axe-1440x1026.jpg');
INSERT INTO public.dictionary VALUES (105, 'підземелля, підземна в''язниця', 'dungeon', 'B1', '3a8ddbfd-c291-4e90-b3e7-dac29f2e3300.MedievalDungeonScreenshot03-1920x1080-5f9d7cbec2ddf27356bc6470cd64ad11.jpg');
INSERT INTO public.dictionary VALUES (106, 'куля, патрон', 'a bullet', 'B2', 'df59bfe4-b3fa-4fcf-b3fd-d1c3d912ac2c.bullet_noun_002_04860.jpg');
INSERT INTO public.dictionary VALUES (107, 'монолог', 'soliloquy', 'B2', '840b021a-add2-425e-924c-b49030b61c7e.hamlets-soliloquy.jpg');
INSERT INTO public.dictionary VALUES (108, 'тротуар', 'sidewalk', 'A2', '8459a632-fad6-4b83-b7be-6bebaa405267.1200px-Gehweg.jpg');
INSERT INTO public.dictionary VALUES (109, 'пасовище', 'a pasturage', 'B2', '8c59ac8c-d60f-46e8-98f4-4666c22763ae.pasturage-esko-lindell.jpg');
INSERT INTO public.dictionary VALUES (110, 'жилет', 'vest', 'B1', 'ada23c11-83cd-439f-901a-d2c599028de6.mens-vest-in-hunter-green.jpg');
INSERT INTO public.dictionary VALUES (111, 'вирішальне', 'cruical', 'B2', NULL);
INSERT INTO public.dictionary VALUES (112, 'берег', 'coast', 'A2', '1db29dd7-3b67-4e80-85f3-f396c7011ded.swcp.png');
INSERT INTO public.dictionary VALUES (113, 'вартий', 'worthwhile', 'B1', NULL);
INSERT INTO public.dictionary VALUES (114, 'продавати', 'to market', 'B1', NULL);
INSERT INTO public.dictionary VALUES (115, 'слідувати', 'to follow', 'A2', NULL);
INSERT INTO public.dictionary VALUES (116, 'захворювання', 'illness', 'A2', NULL);
INSERT INTO public.dictionary VALUES (117, 'кінь', 'horse', 'B1', NULL);
INSERT INTO public.dictionary VALUES (118, 'масло', 'butter', 'A1', NULL);
INSERT INTO public.dictionary VALUES (6, 'суперечити чийсь релігії/переконання', 'against one''s religion/beliefs', 'B1', NULL);
INSERT INTO public.dictionary VALUES (119, 'раковина', 'sink', 'A2', NULL);


--
-- PostgreSQL database dump complete
--

ALTER SEQUENCE public.dictionary_id_seq RESTART WITH 120;