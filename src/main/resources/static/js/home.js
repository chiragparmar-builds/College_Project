(function () {
  'use strict';

  function initReveal() {
    var selectors = '.home-feature, .home-ats__row, .home-team__card, .home-cta__inner';
    var elements = document.querySelectorAll(selectors);
    var observerOptions = { root: null, rootMargin: '0px 0px -40px 0px', threshold: 0.08 };

    var observer = new IntersectionObserver(function (entries) {
      entries.forEach(function (entry) {
        if (entry.isIntersecting) {
          entry.target.classList.add('reveal');
        }
      });
    }, observerOptions);

    elements.forEach(function (el) { observer.observe(el); });
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initReveal);
  } else {
    initReveal();
  }
})();
