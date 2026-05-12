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

// 모바일 햄버거 메뉴 자동 삽입
document.addEventListener('DOMContentLoaded', function() {
    const headerInner = document.querySelector('.header-inner');
    const nav = document.querySelector('.header-inner > .nav');
    if (headerInner && nav && !document.querySelector('.nav-toggle')) {
        const btn = document.createElement('button');
        btn.className = 'nav-toggle';
        btn.setAttribute('aria-label', '메뉴');
        btn.innerHTML = '☰';
        btn.addEventListener('click', function() {
            nav.classList.toggle('open');
        });
        headerInner.appendChild(btn);

        // 네비 링크 클릭 시 메뉴 닫기
        nav.querySelectorAll('a').forEach(function(a) {
            a.addEventListener('click', function() {
                nav.classList.remove('open');
            });
        });
    }
});
