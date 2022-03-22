import '/web/js/jquery-3.5.1.min.js';
import '/web/js/jquery-ui-1.12.1/jquery-ui.min.js';
import '/web/js/jquery-ui-1.12.1/widget.strict.js';
import '/web/js/jquery-ui-1.12.1/widgets/tooltip.js';
import '/web/js/jquery-ui-1.12.1/widgets/datepicker-yearoffset.js';
import '/web/js/jquery-ui-1.12.1/i18n/datepicker-th.js';
import '/web/js/jquery-3.5.1.plugin.js';
import '/web/js/functions.js';
import config from './config.js';
export const fn = {
  date: {
    dif(f, t) {
      let v = {
        year: t.getFullYear() - f.getFullYear(),
        month: t.getMonth() - f.getMonth(),
        date: t.getDate() - f.getDate()
      };
      if (v.date < 0) {
        v.date += 30;
        v.month--;
      }
      if (v.month < 0) {
        v.month += 12;
        v.year--;
      }
      return v;
    }
  }
};