//function example() {
//    var _date = new Date();
//    _date.getYear(); //获取当前年份(2位)
//    _date.getFullYear(); //获取完整的年份(4位,1970-????)
//    _date.getMonth(); //获取当前月份(0-11,0代表1月)
//    _date.getDate(); //获取当前日(1-31)
//    _date.getDay(); //获取当前星期X(0-6,0代表星期天)
//    _date.getTime(); //获取当前时间(从1970.1.1开始的毫秒数)
//    _date.getHours(); //获取当前小时数(0-23)
//    _date.getMinutes(); //获取当前分钟数(0-59)
//    _date.getSeconds(); //获取当前秒数(0-59)
//    _date.getMilliseconds(); //获取当前毫秒数(0-999)
//    _date.toLocaleDateString(); //获取当前日期
//    var mytime = _date.toLocaleTimeString(); //获取当前时间
//    _date.toLocaleString(); //获取日期与时间
//}

/**
 * 日期格式化
 * YYYY/yyyy/YY/yy 年份
 * MM/M 月份
 * W/w 星期
 * dd/DD/d/D 日期
 * hh/HH/h/H 时间
 * mm/m 分钟
 * ss/SS/s/S 秒
 */
Date.prototype.Format = function(formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    var _month = this.getMonth();
    _month++;
    str = str.replace(/MM/, _month > 9 ? _month.toString() : '0' + _month);
    str = str.replace(/M/g, _month);

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}

function dateFormater(date, formatStr) {
    if (isBlank(formatStr)) {
        return date.Format('yyyy-MM-dd HH:mm:ss');
    }
    return date.Format(formatStr);
}

/** 日初 */
function getDayStart(date) {
    var _date = date;
    if (isBlank(_date)) {
        _date = new Date();
    }
    _date.setHours(0);
    _date.setMinutes(0);
    _date.setSeconds(0);
    _date.setMilliseconds(0);
    return _date;
}

/** 日末 */
function getDayEnd(date) {
    var _date = date;
    if (isBlank(_date)) {
        _date = new Date();
    }
    _date.setHours(23);
    _date.setMinutes(59);
    _date.setSeconds(59);
    _date.setMilliseconds(999);
    return _date;
}

/** 前一天 */
function getLastDay(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	var oneDay = 1000 * 60 * 60 * 24;
	_date = new Date(_date.getTime() - oneDay);
	return _date;
}

/** 周初 */
function getWeekStart(date) {
    var _date = date;
    if (isBlank(_date)) {
        _date = new Date();
    }
    var oneDay = 1000 * 60 * 60 * 24;
    if(_date.getDay() == 0) { // 周日
    	var weekFirstDay = new Date(_date.getTime() - 6 * oneDay);
    	return getDayStart(weekFirstDay);
    }
    var weekFirstDay = new Date(_date.getTime() - (_date.getDay() - 1) * oneDay);
    return getDayStart(weekFirstDay);
}

/** 周末 */
function getWeekEnd(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	var oneDay = 1000 * 60 * 60 * 24;
	var weekFirstDay = getWeekStart(_date);
	var weekLastDay = new Date((weekFirstDay.getTime() + 6 * oneDay));
	return getDayEnd(weekLastDay);
}

/** 上周末: 周初的前一天日末 */
function getLastWeekEnd(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	_date = getLastDay(getWeekStart(_date));
	return getDayEnd(_date);
}

/** 上周初: 上周末的周初 */
function getLastWeekStart(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	_date = getWeekStart(getLastWeekEnd(_date));
	return getDayStart(_date);
}

/** 月初: 本月第一天日初 */
function getMonthStart(date) {
    var _date = date;
    if (isBlank(_date)) {
        _date = new Date();
    }
    _date.setDate(1);
    return getDayStart(_date);
}

/** 月末: 下月第一天的前一天日末 */
function getMonthEnd(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	var nextMonth = _date.getMonth() + 1;
	var nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
	var oneDay = 1000 * 60 * 60 * 24;
	_date = new Date(nextMonthFirstDay - oneDay);
	return getDayEnd(_date);
}

/** 上个月末: 本月第一天的前一天日末 */
function getLastMonthEnd(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	_date = getLastDay(getMonthStart(_date));
	return getDayEnd(_date);
}

/** 7天前日初 */
function get7DaysBefore(date) {
	var _date = date;
	if (isBlank(_date)) {
		_date = new Date();
	}
	var oneDay = 1000 * 60 * 60 * 24;
	_date = new Date(_date.getTime() - 7 * oneDay);
	return getDayStart(_date);
}

