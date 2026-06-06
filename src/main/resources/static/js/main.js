document.addEventListener('DOMContentLoaded', function () {
    var filterBtns = document.querySelectorAll('.filter-btn');
    filterBtns.forEach(function (btn) {
        btn.addEventListener('click', function () {
            filterBtns.forEach(function (b) { b.classList.remove('active'); });
            btn.classList.add('active');
        });
    });

    var alerts = document.querySelectorAll('.alert');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            alert.style.transition = 'opacity 0.3s';
            alert.style.opacity = '0';
            setTimeout(function () { alert.remove(); }, 300);
        }, 5000);
    });

    var confirmBtns = document.querySelectorAll('[onclick*="confirm"]');
    confirmBtns.forEach(function (btn) {
        btn.removeAttribute('onclick');
        btn.addEventListener('click', function (e) {
            if (!confirm('确认执行此操作？')) {
                e.preventDefault();
            }
        });
    });
});
