-- 유저 1번(나)에게 매칭 요청 보내는 테스트 데이터
-- 서울 김민준(2), 부산 이나연(12), 경기 이지우(43) → 나(1)에게 요청

INSERT INTO match_requests (sender_id, receiver_id, status, created_at) VALUES
(2, 1, 'PENDING', NOW()),
(12, 1, 'PENDING', NOW()),
(43, 1, 'PENDING', NOW());
