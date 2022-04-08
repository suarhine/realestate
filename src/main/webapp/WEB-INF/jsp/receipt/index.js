import * as contract from '../contract/index.js?unbind';

window.jQuery && (function ($) {
  const event = {
    '[data-page="index"]': {
      '[name="option"]': contract.event.option
    }
  };
  $(document).listen(event);
})(window.jQuery);