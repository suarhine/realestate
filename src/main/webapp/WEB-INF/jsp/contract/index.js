import {fn} from '../js/index.js';
const xport = {event: {}};
window.jQuery && (function ($) {
  const event = {
    '[data-page="index"], [data-pane="lessee"]': {
      '[name="option"]': xport.event.option = {
        click() {
          if (this.dataset.relShowed === undefined) {
            this.dataset.relShowed = '';
            $('span', this).html('ตัวเลือกน้อยลง');
            $(this).jump('form', '[data-pane="option"]').removeAttr('data-rel-hide')
                .find(':input').prop('disabled', false);
          } else {
            delete this.dataset.relShowed;
            $('span', this).html('ตัวเลือกอื่นๆ');
            $(this).jump('form', '[data-pane="option"]').attr('data-rel-hide', '')
                .find(':input').prop('disabled', true);
          }
        }
      }
    }, '[data-page="input"]': {
      init() {
        $('[name="started"]', this).trigger('change');
      }, '[name="active-tap"]': {
        change() {
          $('[data-rel-active-tab-header] > label').removeClass('--active')
              .filter('[for="' + this.id + '"]').prevAll('label').addBack().addClass('--active');

        }
      }, '[data-rel-ctrl-tab]': {
        click() {
          try {
            ({
              next: $ui => $ui.nextAll('input:first'),
              prev: $ui => $ui.prevAll('input:eq(1)')
            })[this.dataset.relCtrlTab]($(this).closest('form > div'))
                .prop('checked', true).trigger('change');
          } catch (x) {
          }
        }
      }, '[name="started"], [name="ended"]': {
        change() {
          let $form = $(this).closest('form');
          let started = new Date($form.name('started').val());
          let ended = new Date($form.name('ended').val());
          ended.setDate(ended.getDate() + 15);
          let dif = fn.date.dif(started, ended);
          let html = '';
          if (dif.year > 0) {
            html += ' ' + dif.year + ' ปี';
          }
          if (dif.month > 0) {
            html += ' ' + dif.month + ' เดือน';
          }
          $form.pane('rental-period').html(html.substr(1));
          $('[data-fee-detail]', $form).trigger('change');
        }
      }, '[data-fee-detail]': {
        change() {
          let $this = $(this);
          let $pane = $this.parents().next().find('[data-pane="payment-plan"]').html('');
          let $form = $this.closest('form');
          let started = new Date($form.name('started').val());
          let ended = new Date($form.name('ended').val());
          if (started > ended) {
            return;
          }
          let $selected = $this.find(':selected');
          let name = this.dataset.feeDetail || this.getAttribute('name');
          let value = this.value;
          switch (value) {
            case '1':
            case '2':
              $this.parent().next().html(`<span class="text-in-input">จำนวนเงิน : </span>
                <input name="${name}.amount" value="${$selected.attr('data-amount') || ''}" style="padding-left: 90px; width: 180px;" />
              `).find('input').on('change keyup', function () {
                $selected.attr('data-amount', this.value);
                if (!+this.value) {
                  $pane.html('');
                  return;
                }
                let period = {
                  1: 'Month',
                  2: 'FullYear'
                }[value];
                let date = [];
                for (let i = new Date(started); i < ended; i['set' + period](i['get' + period]() + 1)) {
                  date.push(new Date(i));
                }
                $pane.html(`
                <table>
                  <thead>
                    <tr>
                     <td class="input-box--value">วันที่</td>
                     <td class="input-box--value">จำนวนเงิน</td>
                    </tr>
                  </thead>
                  <tbody>${date.map(e => `
                    <tr>
                      <td class="input-box--value"><input name="${name}.dating" value="${e.toISOString().substring(0, 10)}" type="hidden" />${e.toLocaleDateString('th', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit'
                  })}</td>
                      <td class="input-box--value"><input name="${name}.dating.amount" value="${this.value}" type="hidden" />${this.value}</td>
                    </tr>
                  `).join('')}</tbody>
                  <tfood>
                    <tr>
                      <td class="input-box--value">รวม</td>
                      <td class="input-box--value">${this.value * date.length}</td>
                    </tr>
                  </tfood>
                </table>
              `);
              }).trigger('keyup');
              break;
            case '3':
              let dating = [{dating: started.toJSON().substr(0, 10), amount: ''}];
              try {
                dating = Function('return ' + $selected.attr('data-dating'))() || dating;
              } catch (x) {
              }
              $this.parent().next().html('').next().html('');
              $pane.html(`
                <table class="-full-input -input-no-border">
                  <thead>
                    <tr>
                     <td class="input-box--value">วันที่</td>
                     <td class="input-box--value">จำนวนเงิน</td>
                    </tr>
                  </thead>
                  <tbody>
                    ${dating.map(e => `
                    <tr data-rel-del>
                      <td class="input-box--value">
                        <input name="${name}.dating" value="${e.dating}" type="date">
                      </td>
                      <td class="input-box--value">
                        <input name="${name}.dating.amount" value="${e.amount}" data-rel-del-key-auto>
                      </td>
                    </tr>
                    `)}
                    <tr data-rel-add data-rel-del>
                      <td class="input-box--value">
                        <input name="${name}.dating" type="date">
                      </td>
                      <td class="input-box--value">
                        <input name="${name}.dating.amount" data-rel-del-key-auto>
                      </td>
                    </tr>
                  </tbody>
                  <tfood>
                    <tr>
                      <td class="input-box--value">รวม</td>
                      <td data-pane="sum" class="input-box--value"></td>
                    </tr>
                  </tfood>
                </table>
              `).find('table').on('change keyup', function () {
                let sum = $(`[name="${name}.dating.amount"]`).map(function () {
                  return +this.value || 0;
                }).reduce((p, e) => p + e);
                $('[data-pane="sum"]', this).html(sum);
              }).trigger('change');
              break;
          }
        }
      }, '[data-fee-period]': {
        keyup() {
          $(this).closest('tr').next().find(':last').html(`
          `);
        }
      }, '[name="dated"]': {
        change() {
          let v = new Date(this.value);
          $('[name="plan.deadline_monthly"], [name="plan.deadline_yearly_date"]').val(v.getDate());
          $('[name="plan.deadline_yearly"]').val(v.getMonth());
        }
      }, '[name="plan.finerate"]': {
        change() {
          switch (this.nodeName.toLocaleLowerCase()) {
            case 'select':
              if (this.value === '+') {
                let i = document.createElement('input');
                i.setAttribute('name', 'plan.finerate');
                this.replaceWith(i);
                i.focus();
              }
              break;
            case 'input':
              if (!this.value || +this.value === 1.25 || +this.value === 1.5) {
                this.replaceWith($(`
                  <select name="plan.finerate">
                    <option value=""></option>
                    <option value="1.25" ${+this.value === 1.25 ? 'selected' : ''}>1.25</option>
                    <option value="1.50" ${+this.value === 1.5 ? 'selected' : ''}>1.50</option>
                    <option value="+">...</option>
                  </select>
                `)[0]);
              }
              break;
          }
        }
      }, '[data-rel-attach]': {
        'rel-attach'(e, ui) {
          let $f = $(this).closest('form');
          for (let o of ui.files) {
            let r = new FileReader();
            r.onload = e => {
              let r = e.target.result;
              let poe = r.indexOf(',');
              $f.find('[data-pane="attach-link"]').append(`
                <div class="row" data-rel-del>
                  <div class="input-box">
                    <label class="input-box--field">
                      <a data-ref="${e.target.result}" data-ref-dialog="{modal: true, width: '85vw'}">${o.name}</a>
                      <input name="attach.id" type="hidden" />
                      <input name="attach.name" type="hidden" value="${o.name}" />
                      <input name="attach.type" type="hidden" value="${r.substring(5, poe - 7)}" />
                      <input name="attach.value" type="hidden" value="${r.substring(poe + 1)}" />
                    </label>
                    <span data-rel-del-act>❌</span>
                  </div>
                </div>
              `);
            };
            r.readAsDataURL(o);
          }
          $(ui).remove();
        }
      }
    }
  };
  new URL(import.meta.url).searchParams.get('unbind') === null && $(document).listen(event);
})(window.jQuery);
export const {event} = xport;