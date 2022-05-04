import {fn} from './js/index.js';
window.jQuery && (function ($) {
  $(document).listen({
    '[data-pane="main-menu"]': {
      click(e) {
        if (e.target.nodeName === 'A') {
          let $item = $(this).children();
          $item.filter('.--active').removeClass('--active');
          $(e.target).closest($item).addClass('--active');
        }
      }
    }
  });
})(window.jQuery);