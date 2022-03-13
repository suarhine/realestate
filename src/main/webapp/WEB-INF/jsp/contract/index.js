import '../js/index.js';
import {fn} from '../js/index.js';
window.jQuery && (function ($) {
  $(document).listen({
    '[data-page="input"]': {
      init() {
        $('[name="started"]', this).trigger('change');
      }, '[name="started"], [name="ended"]': {
        change() {
          let $form = $(this).closest('form');
          let started = new Date($form.name('started').val());
          let ended = new Date($form.name('ended').val());
          ended.setDate(ended.getDate() + 15);
          let dif = fn.date.dif(started, ended);
          let html = '';
          if(dif.year > 0) {
            html += ' ' + dif.year + ' ปี';
          }
          if(dif.month > 0) {
            html += ' ' + dif.month + ' เดือน';
          }
          $form.pane('rental-period').html(html.substr(1));
          $('[data-fee-detail]', $form).trigger('change');
        }
      }, '[data-fee-detail]': {
        change() {
          let $this = $(this);
          let $pane = $this.closest('tr').next().find('>:last').html('');
          let $form = $this.closest('form');
          let started = new Date($form.name('started').val());
          let ended = new Date($form.name('ended').val());
          if (started > ended) {
            return;
          }
          let name = this.dataset.feeDetail;
          let value = this.value;
          switch (value) {
            case '1':
            case '2':
              $this.parent().next().html('จำนวนเงิน').next().html(`
                <input name="${this.dataset.feeDetail}_period_amount" value="${$this.find(':selected').attr('data-curr-period-amount') || ''}" />
              `).find('input').on('change keyup', function () {
                $this.find(':selected').attr('data-curr-period-amount', this.value);
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
                     <td>วันที่</td>
                     <td>จำนวนเงิน</td>
                    </tr>
                  </thead>
                  <tbody>${date.map(e => `
                    <tr>
                      <td>${e.toLocaleDateString('th', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit'
                  })}</td>
                      <td>${this.value}</td>
                    </tr>
                  `).join('')}</tbody>
                  <tfood>
                    <tr>
                      <td>รวม</td>
                      <td>${this.value * date.length}</td>
                    </tr>
                  </tfood>
                </table>
              `);
              }).trigger('keyup');
              break;
            case '3':
              $this.parent().next().html('').next().html('');
              $pane.html(`
                <table class="-input-no-border">
                  <thead>
                    <tr>
                     <td>วันที่</td>
                     <td>จำนวนเงิน</td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr data-rel-del>
                      <td>
                        <input name="${name}.dating" value="${started.toJSON().substr(0, 10)}" type="date">
                      </td>
                      <td>
                        <input name="${name}.amout" data-rel-del-key-auto>
                      </td>
                    </tr>
                    <tr data-rel-add data-rel-del>
                      <td>
                        <input name="${name}.dating" type="date">
                      </td>
                      <td>
                        <input name="${name}.amout" data-rel-del-key-auto>
                      </td>
                    </tr>
                  </tbody>
                  <tfood>
                    <tr>
                      <td>รวม</td>
                      <td data-pane="sum"></td>
                    </tr>
                  </tfood>
                </table>
              `).find('table').on('change keyup', function () {
                let sum = $(`[name="${name}.amout"]`).map(function () {
                  return +this.value || 0;
                }).reduce((p, e) => p + e);
                $('[data-pane="sum"]', this).html(sum);
              });
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
              if(this.value === '+') {
                let i = document.createElement('input');
                i.setAttribute('name', 'plan.finerate');
                this.replaceWith(i);
                i.focus();
              }
              break;
            case 'input':
              if(!this.value || +this.value === 1.25 || +this.value === 1.5) {
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
      }
    }
  });
})(window.jQuery);