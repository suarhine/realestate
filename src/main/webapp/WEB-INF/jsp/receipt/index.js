import * as contract from '../contract/index.js?unbind';

window.jQuery && (function ($) {
  const event = {
    '[data-page="index"], [data-page="input"]': {
      '[name="option"]': contract.event.option
    }, '[data-page="input"]': {
      '[data-rel-select-all]': {
        change() {
          $(this).closest('tbody').next().find('[name="selected"]')
              .prop('checked', this.checked).trigger('change');
        }
      }, 'tbody': {
        keyup() {
          let sum = $('[data-pane="receive"] input', this).get().map(e => +e.value || 0).reduce((p, e) => p + e, 0);
          $(this).prev().find('[data-pane="receive"]').html(sum ? `<input readonly value="${sum.toFixed(2)}" />` : '');
        }, '[name="selected"]': {
          change() {
            let $this = $(this);
            $this.closest('tbody').prev().find('[data-rel-select-all]')
                .prop('checked', $this.jump('tbody', '[name="selected"]:not(:checked)').length === 0);
            $this.jump('tr', '[data-pane="receive"]').html(this.checked ? function () {
              return `
              <input name="${$this.val()}" value="${((+this.dataset.amount || 0) + (+this.dataset.fine || 0)).toFixed(2)}" type="text" />
            `;
            } : '').trigger('keyup');
          }
        }
      }
    }
  };
  $(document).listen(event);
})(window.jQuery);