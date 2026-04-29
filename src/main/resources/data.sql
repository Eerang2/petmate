-- Pet Mate 테스트 데이터
-- 5개 지역 (서울, 부산, 대구, 인천, 경기) x 10명 = 50명

-- ===== 서울 =====
INSERT INTO users (name, age, login_id, password, profile_image, address, latitude, longitude) VALUES
('김민준', 28, 'seoul01', '1234', '/uploads/profile_seoul01.jpg', '서울특별시 강남구 역삼동', 37.5013, 127.0396),
('이서연', 24, 'seoul02', '1234', '/uploads/profile_seoul02.jpg', '서울특별시 마포구 합정동', 37.5496, 126.9139),
('박지호', 32, 'seoul03', '1234', '/uploads/profile_seoul03.jpg', '서울특별시 송파구 잠실동', 37.5133, 127.1001),
('최수아', 27, 'seoul04', '1234', '/uploads/profile_seoul04.jpg', '서울특별시 서초구 반포동', 37.5083, 126.9956),
('정하준', 35, 'seoul05', '1234', '/uploads/profile_seoul05.jpg', '서울특별시 종로구 삼청동', 37.5816, 126.9818),
('강지유', 22, 'seoul06', '1234', '/uploads/profile_seoul06.jpg', '서울특별시 성동구 성수동', 37.5445, 127.0557),
('윤도윤', 30, 'seoul07', '1234', '/uploads/profile_seoul07.jpg', '서울특별시 영등포구 여의도동', 37.5219, 126.9245),
('임서준', 26, 'seoul08', '1234', '/uploads/profile_seoul08.jpg', '서울특별시 용산구 이태원동', 37.5340, 126.9948),
('한지민', 29, 'seoul09', '1234', '/uploads/profile_seoul09.jpg', '서울특별시 노원구 상계동', 37.6541, 127.0625),
('오예준', 33, 'seoul10', '1234', '/uploads/profile_seoul10.jpg', '서울특별시 관악구 신림동', 37.4842, 126.9293);

-- ===== 부산 =====
INSERT INTO users (name, age, login_id, password, profile_image, address, latitude, longitude) VALUES
('김태우', 25, 'busan01', '1234', '/uploads/profile_busan01.jpg', '부산광역시 해운대구 우동', 35.1631, 129.1635),
('이나연', 31, 'busan02', '1234', '/uploads/profile_busan02.jpg', '부산광역시 수영구 광안동', 35.1532, 129.1187),
('박준혁', 28, 'busan03', '1234', '/uploads/profile_busan03.jpg', '부산광역시 부산진구 전포동', 35.1580, 129.0636),
('최윤서', 23, 'busan04', '1234', '/uploads/profile_busan04.jpg', '부산광역시 남구 대연동', 35.1368, 129.0843),
('정시우', 36, 'busan05', '1234', '/uploads/profile_busan05.jpg', '부산광역시 동래구 온천동', 35.2050, 129.0787),
('강소율', 27, 'busan06', '1234', '/uploads/profile_busan06.jpg', '부산광역시 사하구 하단동', 35.1047, 128.9665),
('윤지안', 34, 'busan07', '1234', '/uploads/profile_busan07.jpg', '부산광역시 금정구 장전동', 35.2298, 129.0893),
('임하린', 21, 'busan08', '1234', '/uploads/profile_busan08.jpg', '부산광역시 연제구 거제동', 35.1826, 129.0663),
('한서윤', 29, 'busan09', '1234', '/uploads/profile_busan09.jpg', '부산광역시 사상구 주례동', 35.1526, 129.0175),
('오건우', 30, 'busan10', '1234', '/uploads/profile_busan10.jpg', '부산광역시 중구 남포동', 35.0975, 129.0325);

-- ===== 대구 =====
INSERT INTO users (name, age, login_id, password, profile_image, address, latitude, longitude) VALUES
('김현우', 26, 'daegu01', '1234', '/uploads/profile_daegu01.jpg', '대구광역시 수성구 범어동', 35.8588, 128.6301),
('이다은', 33, 'daegu02', '1234', '/uploads/profile_daegu02.jpg', '대구광역시 중구 동성로', 35.8690, 128.5954),
('박서진', 24, 'daegu03', '1234', '/uploads/profile_daegu03.jpg', '대구광역시 달서구 상인동', 35.8262, 128.5327),
('최예린', 28, 'daegu04', '1234', '/uploads/profile_daegu04.jpg', '대구광역시 북구 침산동', 35.8867, 128.5837),
('정우진', 31, 'daegu05', '1234', '/uploads/profile_daegu05.jpg', '대구광역시 동구 신암동', 35.8821, 128.6340),
('강하은', 22, 'daegu06', '1234', '/uploads/profile_daegu06.jpg', '대구광역시 남구 대명동', 35.8468, 128.5803),
('윤준서', 37, 'daegu07', '1234', '/uploads/profile_daegu07.jpg', '대구광역시 달성군 다사읍', 35.8738, 128.4576),
('임채원', 25, 'daegu08', '1234', '/uploads/profile_daegu08.jpg', '대구광역시 서구 비산동', 35.8696, 128.5587),
('한지훈', 29, 'daegu09', '1234', '/uploads/profile_daegu09.jpg', '대구광역시 수성구 만촌동', 35.8538, 128.6413),
('오수빈', 32, 'daegu10', '1234', '/uploads/profile_daegu10.jpg', '대구광역시 동구 동대구역', 35.8797, 128.6286);

-- ===== 인천 =====
INSERT INTO users (name, age, login_id, password, profile_image, address, latitude, longitude) VALUES
('김이준', 27, 'incheon01', '1234', '/uploads/profile_incheon01.jpg', '인천광역시 남동구 구월동', 37.4505, 126.7310),
('이소희', 30, 'incheon02', '1234', '/uploads/profile_incheon02.jpg', '인천광역시 연수구 송도동', 37.3809, 126.6569),
('박민서', 23, 'incheon03', '1234', '/uploads/profile_incheon03.jpg', '인천광역시 부평구 부평동', 37.5075, 126.7219),
('최시윤', 35, 'incheon04', '1234', '/uploads/profile_incheon04.jpg', '인천광역시 계양구 작전동', 37.5350, 126.7372),
('정유나', 26, 'incheon05', '1234', '/uploads/profile_incheon05.jpg', '인천광역시 미추홀구 주안동', 37.4641, 126.6810),
('강도현', 34, 'incheon06', '1234', '/uploads/profile_incheon06.jpg', '인천광역시 서구 검단동', 37.5870, 126.6636),
('윤아인', 28, 'incheon07', '1234', '/uploads/profile_incheon07.jpg', '인천광역시 중구 신포동', 37.4735, 126.6237),
('임서현', 22, 'incheon08', '1234', '/uploads/profile_incheon08.jpg', '인천광역시 남동구 논현동', 37.4073, 126.7296),
('한예서', 31, 'incheon09', '1234', '/uploads/profile_incheon09.jpg', '인천광역시 부평구 산곡동', 37.5098, 126.7044),
('오시후', 29, 'incheon10', '1234', '/uploads/profile_incheon10.jpg', '인천광역시 연수구 동춘동', 37.3990, 126.6805);

-- ===== 경기 =====
INSERT INTO users (name, age, login_id, password, profile_image, address, latitude, longitude) VALUES
('김하율', 24, 'gg01', '1234', '/uploads/profile_gg01.jpg', '경기도 성남시 분당구 정자동', 37.3659, 127.1085),
('이지우', 33, 'gg02', '1234', '/uploads/profile_gg02.jpg', '경기도 수원시 영통구 광교동', 37.2897, 127.0487),
('박도윤', 28, 'gg03', '1234', '/uploads/profile_gg03.jpg', '경기도 고양시 일산서구 주엽동', 37.6733, 126.7521),
('최하윤', 21, 'gg04', '1234', '/uploads/profile_gg04.jpg', '경기도 용인시 수지구 동천동', 37.3259, 127.0994),
('정예은', 36, 'gg05', '1234', '/uploads/profile_gg05.jpg', '경기도 화성시 동탄동', 37.2006, 127.0638),
('강시온', 25, 'gg06', '1234', '/uploads/profile_gg06.jpg', '경기도 파주시 운정동', 37.7153, 126.7576),
('윤채은', 30, 'gg07', '1234', '/uploads/profile_gg07.jpg', '경기도 안양시 동안구 평촌동', 37.3896, 126.9516),
('임건호', 27, 'gg08', '1234', '/uploads/profile_gg08.jpg', '경기도 김포시 장기동', 37.6287, 126.7150),
('한소윤', 34, 'gg09', '1234', '/uploads/profile_gg09.jpg', '경기도 의정부시 민락동', 37.7503, 127.0742),
('오지율', 23, 'gg10', '1234', '/uploads/profile_gg10.jpg', '경기도 광주시 오포읍', 37.3608, 127.2355);


-- ===== 펫 데이터 (users id 순서대로) =====

-- 서울 (1~10)
INSERT INTO pets (user_id, pet_image, pet_type, pet_breed, personality) VALUES
(1, '/uploads/pet_seoul01.jpg', '강아지', '골든 리트리버', '온순하고 사람을 매우 좋아함'),
(2, '/uploads/pet_seoul02.jpg', '고양이', NULL, '도도하지만 주인에게는 애교쟁이'),
(3, '/uploads/pet_seoul03.jpg', '강아지', '시바견', '독립적이지만 산책을 좋아함'),
(4, '/uploads/pet_seoul04.jpg', '강아지', '푸들', '영리하고 활발함, 다른 강아지와 잘 어울림'),
(5, '/uploads/pet_seoul05.jpg', '강아지', '진돗개', '충성스럽고 용감함, 낯선 사람에게 경계'),
(6, '/uploads/pet_seoul06.jpg', '고양이', NULL, '호기심이 많고 장난기 넘침'),
(7, '/uploads/pet_seoul07.jpg', '강아지', '웰시코기', '밝고 활기차며 산책을 매우 좋아함'),
(8, '/uploads/pet_seoul08.jpg', '강아지', '말티즈', '애교가 많고 순한 성격'),
(9, '/uploads/pet_seoul09.jpg', '강아지', '비숑 프리제', '쾌활하고 사교적, 아이들과도 잘 놀아요'),
(10, '/uploads/pet_seoul10.jpg', '강아지', '믹스견', '겁이 좀 있지만 친해지면 다정함');

-- 부산 (11~20)
INSERT INTO pets (user_id, pet_image, pet_type, pet_breed, personality) VALUES
(11, '/uploads/pet_busan01.jpg', '강아지', '포메라니안', '작지만 에너지 넘치고 호기심 많음'),
(12, '/uploads/pet_busan02.jpg', '강아지', '래브라도 리트리버', '순둥순둥하고 물놀이를 좋아함'),
(13, '/uploads/pet_busan03.jpg', '고양이', NULL, '조용하고 차분한 성격, 구석에서 관찰하는 걸 좋아해요'),
(14, '/uploads/pet_busan04.jpg', '강아지', '치와와', '작지만 용감하고 주인에게 충성적'),
(15, '/uploads/pet_busan05.jpg', '강아지', '허스키', '활동량 많고 매우 친근함, 산책 필수'),
(16, '/uploads/pet_busan06.jpg', '강아지', '불독', '느긋하고 온화함, 느린 산책을 좋아해요'),
(17, '/uploads/pet_busan07.jpg', '토끼', NULL, '온순하고 조용해요, 손에 올려놓으면 가만히 있어요'),
(18, '/uploads/pet_busan08.jpg', '강아지', '말티즈', '사람을 좋아하고 꼬리를 많이 흔들어요'),
(19, '/uploads/pet_busan09.jpg', '강아지', '시바견', '고집이 좀 있지만 매력적이에요'),
(20, '/uploads/pet_busan10.jpg', '고양이', NULL, '길냥이 출신인데 이제 완전 집냥이');

-- 대구 (21~30)
INSERT INTO pets (user_id, pet_image, pet_type, pet_breed, personality) VALUES
(21, '/uploads/pet_daegu01.jpg', '강아지', '진돗개', '씩씩하고 건강한 친구, 아침 산책 좋아해요'),
(22, '/uploads/pet_daegu02.jpg', '강아지', '푸들', '미니 푸들이고 영리하고 사교적'),
(23, '/uploads/pet_daegu03.jpg', '고양이', NULL, '느긋하고 낮잠을 좋아해요'),
(24, '/uploads/pet_daegu04.jpg', '강아지', '웰시코기', '다리가 짧지만 달리기를 좋아함'),
(25, '/uploads/pet_daegu05.jpg', '강아지', '골든 리트리버', '누구에게나 친절한 대형견'),
(26, '/uploads/pet_daegu06.jpg', '햄스터', NULL, '작고 귀여워요, 쳇바퀴 돌리는 걸 좋아해요'),
(27, '/uploads/pet_daegu07.jpg', '강아지', '믹스견', '착하고 순한 성격, 산책 갈 때 신나함'),
(28, '/uploads/pet_daegu08.jpg', '강아지', '포메라니안', '짖는 걸 좋아하지만 겁쟁이'),
(29, '/uploads/pet_daegu09.jpg', '강아지', '비숑 프리제', '하얀 솜사탕 같고 사람 좋아해요'),
(30, '/uploads/pet_daegu10.jpg', '고양이', NULL, '츤데레 성격, 기분 좋으면 골골송');

-- 인천 (31~40)
INSERT INTO pets (user_id, pet_image, pet_type, pet_breed, personality) VALUES
(31, '/uploads/pet_incheon01.jpg', '강아지', '시바견', '산책을 매우 좋아하고 밖에만 나가면 신남'),
(32, '/uploads/pet_incheon02.jpg', '강아지', '래브라도 리트리버', '온순하고 차분함, 훈련 잘 받아요'),
(33, '/uploads/pet_incheon03.jpg', '강아지', '말티즈', '애교쟁이, 안아달라고 앞발 올려요'),
(34, '/uploads/pet_incheon04.jpg', '고양이', NULL, '활발한 고양이, 놀이시간이 제일 좋아요'),
(35, '/uploads/pet_incheon05.jpg', '강아지', '치와와', '작고 깜찍하지만 의외로 활동적'),
(36, '/uploads/pet_incheon06.jpg', '강아지', '허스키', '털이 많이 빠지지만 매력 만점'),
(37, '/uploads/pet_incheon07.jpg', '새', NULL, '앵무새, 말 따라하는 걸 좋아해요'),
(38, '/uploads/pet_incheon08.jpg', '강아지', '골든 리트리버', '대형견이지만 순한 양'),
(39, '/uploads/pet_incheon09.jpg', '강아지', '푸들', '산책 중 다른 강아지 보면 꼬리 흔들어요'),
(40, '/uploads/pet_incheon10.jpg', '강아지', '불독', '게으르지만 귀여운 매력이 있어요');

-- 경기 (41~50)
INSERT INTO pets (user_id, pet_image, pet_type, pet_breed, personality) VALUES
(41, '/uploads/pet_gg01.jpg', '강아지', '웰시코기', '엉덩이가 매력적이고 산책을 좋아해요'),
(42, '/uploads/pet_gg02.jpg', '강아지', '진돗개', '한국 토종견, 건강하고 충직함'),
(43, '/uploads/pet_gg03.jpg', '강아지', '포메라니안', '작은 여우 같은 외모에 애교만점'),
(44, '/uploads/pet_gg04.jpg', '고양이', NULL, '먼치킨이에요, 짧은 다리가 귀여워요'),
(45, '/uploads/pet_gg05.jpg', '강아지', '골든 리트리버', '가족 모두에게 사랑받는 대형견'),
(46, '/uploads/pet_gg06.jpg', '강아지', '비숑 프리제', '곱슬곱슬 하얀 털, 알러지 걱정 없어요'),
(47, '/uploads/pet_gg07.jpg', '강아지', '시바견', '귀여운 시바 스마일이 매력적'),
(48, '/uploads/pet_gg08.jpg', '토끼', NULL, '롭이어 토끼, 귀가 축 처져서 귀여워요'),
(49, '/uploads/pet_gg09.jpg', '강아지', '믹스견', '유기견 출신인데 지금은 완전 행복한 아이'),
(50, '/uploads/pet_gg10.jpg', '강아지', '허스키', '운동량이 많아서 매일 산책 필수');


-- ===== 커뮤니티 게시글 샘플 데이터 =====

-- 우리 애 자랑
INSERT INTO community_posts (user_id, title, content, tag, created_at) VALUES
(1, '우리 골든이 오늘 미용 다녀왔어요!', '미용실 갔다 왔는데 완전 다른 강아지가 된 것 같아요ㅋㅋ 너무 잘생겨져서 왔네요. 미용사 선생님이 성격 너무 좋다고 칭찬해주셨어요!', '우리 애 자랑', '2026-04-17 10:30:00'),
(4, '푸들 자랑합니다~ 우리 콩이', '우리 콩이가 오늘 앉아, 기다려, 손 다 성공했어요!! 영리한 우리 콩이 칭찬해주세요~', '우리 애 자랑', '2026-04-17 14:20:00'),
(8, '말티즈 두부의 하루', '아침에 눈 뜨자마자 꼬리 흔들면서 달려오는 두부... 세상에서 제일 행복한 아침이에요. 사진 봐주세요!', '우리 애 자랑', '2026-04-16 09:15:00'),
(22, '푸들 미니 쵸코 자랑!', '쵸코가 오늘 첫 수영을 했어요! 물에 들어가니까 본능적으로 수영하더라고요. 너무 귀여웠어요.', '우리 애 자랑', '2026-04-15 16:45:00');

-- 산책 후기
INSERT INTO community_posts (user_id, title, content, tag, created_at) VALUES
(7, '여의도 한강공원 산책 후기', '오늘 코기랑 여의도 한강공원 다녀왔어요! 벚꽃은 졌지만 초록초록한 잔디밭에서 뛰어놀았습니다. 강아지 동반 가능한 구역이 넓어서 좋았어요.', '산책 후기', '2026-04-17 18:00:00'),
(3, '잠실 석촌호수 산책 추천!', '시바견이랑 석촌호수 한 바퀴 돌았는데 딱 40분 정도 걸려요. 산책로가 잘 되어있고 강아지 친구들도 많아서 시바가 신나했어요.', '산책 후기', '2026-04-16 17:30:00'),
(15, '해운대 해변 산책 완전 추천', '허스키랑 해운대 해변 산책했는데 모래사장에서 미친듯이 뛰어다녔어요ㅋㅋ 바다 바람 맞으면서 산책하니까 기분 최고!', '산책 후기', '2026-04-15 19:00:00'),
(41, '분당 율동공원 산책 코스', '코기랑 율동공원 다녀왔어요. 호수 한 바퀴 돌면 30분 정도고 벤치도 많아서 쉬엄쉬엄 산책하기 좋아요. 주말에는 사람이 많으니 평일 추천!', '산책 후기', '2026-04-14 15:20:00');

-- 질문게시판
INSERT INTO community_posts (user_id, title, content, tag, created_at) VALUES
(6, '고양이 사료 추천 부탁드려요!', '6개월 된 고양이를 키우고 있는데 사료를 뭘로 바꿔야 할지 모르겠어요. 지금은 키튼용 먹이고 있는데 성묘용으로 언제쯤 바꾸면 될까요?', '질문게시판', '2026-04-17 11:00:00'),
(14, '강아지 짖음 훈련 어떻게 하나요?', '치와와가 초인종만 울리면 미친듯이 짖어요ㅠㅠ 동네 민원 올까봐 걱정인데 짖음 훈련 성공하신 분 팁 좀 알려주세요!', '질문게시판', '2026-04-16 20:30:00'),
(26, '햄스터 케이지 추천해주세요', '햄스터를 처음 키우는데 케이지가 너무 작은 것 같아요. 넉넉한 사이즈로 바꾸고 싶은데 추천 제품 있을까요?', '질문게시판', '2026-04-15 13:10:00');

-- 번개산책모집
INSERT INTO community_posts (user_id, title, content, tag, created_at) VALUES
(2, '합정역 근처 오늘 저녁 산책 같이 하실 분!', '오늘 7시쯤 망원한강공원에서 산책하려고 해요! 고양이는 집에 두고 혼자 산책하려다가 같이할 친구가 있으면 좋겠어서요. 강아지 데리고 오셔도 돼요~', '번개산책모집', '2026-04-18 15:00:00'),
(31, '구월동 근처 주말 산책 모임', '토요일 오전 10시에 인천대공원에서 산책 모임 하려고 합니다! 시바견 데리고 갈 건데 다른 친구들도 환영이요~', '번개산책모집', '2026-04-17 21:00:00'),
(45, '분당 중앙공원 내일 아침 산책', '내일(토) 아침 8시에 중앙공원에서 산책하실 분~ 골든이랑 가는데 대형견 친구 있으면 좋겠어요!', '번개산책모집', '2026-04-17 22:30:00');

-- 매칭 후기
INSERT INTO community_posts (user_id, title, content, tag, created_at) VALUES
(9, '비숑이랑 푸들 매칭 성공 후기!', '매칭으로 만난 푸들 콩이네와 비숑 몽이가 첫 만남에 바로 친해졌어요! 서로 냄새 맡고 같이 뛰어다니는 모습이 너무 귀여웠어요. Pet Mate 최고!', '매칭 후기', '2026-04-17 12:00:00'),
(12, '래브라도끼리 만났어요!', '매칭된 분이 같은 래브라도를 키우시더라고요! 해운대에서 만나서 같이 산책했는데 두 마리가 쌍둥이 같았어요ㅋㅋ 다음에 또 만나기로 했어요!', '매칭 후기', '2026-04-16 14:00:00'),
(33, '말티즈 친구 만들어줬어요', '매칭으로 같은 동네 말티즈 키우시는 분을 만났어요. 우리 아이들이 너무 잘 놀아서 이제 매주 만나기로 했답니다!', '매칭 후기', '2026-04-15 11:30:00');


-- ===== 커뮤니티 게시글 이미지 =====
INSERT INTO post_images (post_id, image_url, sort_order) VALUES
(1, '/uploads/post_grooming_01.jpg', 0),
(1, '/uploads/post_grooming_02.jpg', 1),
(2, '/uploads/post_poodle_trick_01.jpg', 0),
(3, '/uploads/post_maltese_morning_01.jpg', 0),
(3, '/uploads/post_maltese_morning_02.jpg', 1),
(4, '/uploads/post_poodle_swim_01.jpg', 0),
(5, '/uploads/post_hangang_walk_01.jpg', 0),
(5, '/uploads/post_hangang_walk_02.jpg', 1),
(5, '/uploads/post_hangang_walk_03.jpg', 2),
(6, '/uploads/post_seokchon_walk_01.jpg', 0),
(7, '/uploads/post_haeundae_walk_01.jpg', 0),
(7, '/uploads/post_haeundae_walk_02.jpg', 1),
(8, '/uploads/post_yuldong_walk_01.jpg', 0),
(9, '/uploads/post_cat_food_01.jpg', 0),
(14, '/uploads/post_match_bichon_01.jpg', 0),
(14, '/uploads/post_match_bichon_02.jpg', 1),
(15, '/uploads/post_match_lab_01.jpg', 0),
(16, '/uploads/post_match_maltese_01.jpg', 0);
