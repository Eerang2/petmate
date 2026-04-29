// 로그인 상태 확인
function getUserKey() {
    return localStorage.getItem('userKey');
}

function isLoggedIn() {
    return getUserKey() !== null;
}

function logout() {
    localStorage.removeItem('userKey');
    location.href = '/index.html';
}

// 이미지 미리보기
function previewImage(input, previewId) {
    const preview = document.getElementById(previewId);
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// 강아지 종류 선택 시 견종 표시/숨김
function toggleDogBreed() {
    const petType = document.getElementById('petType');
    const dogBreedGroup = document.getElementById('dogBreedGroup');
    if (petType && dogBreedGroup) {
        dogBreedGroup.style.display = petType.value === '강아지' ? 'block' : 'none';
    }
}

// API 호출 헬퍼
async function apiCall(url, options = {}) {
    const response = await fetch(url, options);
    return response.json();
}
