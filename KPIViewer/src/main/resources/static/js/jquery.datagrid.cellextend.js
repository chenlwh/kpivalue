(function ($) {

//开启编辑单元格状态
function beginEditCell(target, options) {

var opts = $.data(target, "datagrid").options;
var tr = opts.finder.getTr(target, options.index);
var row = opts.finder.getRow(target, options.index);

// //暂时还不知道该代码的含义,忽略使用
// if (tr.hasClass("datagrid-row-editing")) {
// return;
// }
// tr.addClass("datagrid-row-editing");

_initCellEditor(target, options.index, options.field);
_outerWidthOfEditable(target);
//$.validateRow(target, options.index);暂时先不使用,不知道该方法作用
}

function _initCellEditor(target, _index, _field) {
var opts = $.data(target, "datagrid").options;
var tr = opts.finder.getTr(target, _index);
var row = opts.finder.getRow(target, _index);

tr.children("td").each(function () {
var cell = $(this).find("div.datagrid-cell");
var field = $(this).attr("field");

if (field == _field) {//找到与传递参数相同field的单元格
var col = $(target).datagrid("getColumnOption", field);
if (col && col.editor) {
var editorType, editorOp;
if (typeof col.editor == "string") {
editorType = col.editor;
} else {
editorType = col.editor.type;
editorOp = col.editor.options;
}
var editor = opts.editors[editorType];
if (editor) {
var html = cell.html();
var outerWidth = cell._outerWidth();
cell.addClass("datagrid-editable");
cell._outerWidth(outerWidth);
cell.html("<table border=\"0\" cellspacing=\"0\" cellpadding=\"1\"><tr><td></td></tr></table>");
cell.children("table").bind(
"click dblclick contextmenu",
function (e) {
e.stopPropagation();
});
$.data(cell[0], "datagrid.editor", {
actions: editor,
target: editor.init(cell.find("td"),
editorOp),
field: field,
type: editorType,
oldHtml: html
});
}
}

tr.find("div.datagrid-editable").each(function () {
var field = $(this).parent().attr("field");
var ed = $.data(this, "datagrid.editor");
ed.actions.setValue(ed.target, row[field]);
});
}
});
}

//为可编辑的单元格设置外边框
//来自jquery.easyui.1.8.0.js的 function _4d8()方法
function _outerWidthOfEditable(target) {
var dc = $.data(target, "datagrid").dc;
dc.view.find("div.datagrid-editable").each(function () {
var _this = $(this);
var field = _this.parent().attr("field");
var col = $(target).datagrid("getColumnOption", field);
_this._outerWidth(col.width);
var ed = $.data(this, "datagrid.editor");
if (ed.actions.resize) {
ed.actions.resize(ed.target, _this.width());
}
});
}

//关闭编辑单元格状态
function endEditCell(target, options) {
var opts = $.data(target, "datagrid").options;

var updatedRows = $.data(target, "datagrid").updatedRows;
var insertedRows = $.data(target, "datagrid").insertedRows;

var tr = opts.finder.getTr(target, options.index);
var row = opts.finder.getRow(target, options.index);

// //与beginEditCell相呼应,暂时取消
// if (!tr.hasClass("datagrid-row-editing")) {//行不能编辑时,返回
// return;
// }
// tr.removeClass("datagrid-row-editing");

var _535 = false;
var _536 = {};
tr.find("div.datagrid-editable").each(function () {
var _537 = $(this).parent().attr("field");
var ed = $.data(this, "datagrid.editor");
var _538 = ed.actions.getValue(ed.target);
if (row[_537] != _538) {
row[_537] = _538;
_535 = true;
_536[_537] = _538;
}
});
if (_535) {
if (_45f(insertedRows, row) == -1) {
if (_45f(insertedRows, row) == -1) {
updatedRows.push(row);
}
}
}

_destroyCellEditor(target, options);
$(target).datagrid("refreshRow", options.index);
opts.onAfterEdit.call(target, options.index, row, _536);
}

function _45f(a, o) {
for (var i = 0, len = a.length; i < len; i++) {
if (a[i] == o) {
return i;
}
}
return -1;
}

//销毁单元格编辑器
function _destroyCellEditor(target, options) {

var opts = $.data(target, "datagrid").options;
var tr = opts.finder.getTr(target, options.index);

tr.children("td").each(function () {
var field = $(this).attr("field");

if (field == options.field) {//找到与传递参数相同field的单元格

var cell = $(this).find("div.datagrid-editable");
if (cell.length) {
var ed = $.data(cell[0], "datagrid.editor");
if (ed.actions.destroy) {
ed.actions.destroy(ed.target);
}
cell.html(ed.oldHtml);
$.removeData(cell[0], "datagrid.editor");
cell.removeClass("datagrid-editable");
cell.css("width", "");
}
}
});
}

$.extend($.fn.datagrid.methods, {
beginEditCell: function (target, options) {
return target.each(function () {
beginEditCell(this, options);
});
},
endEditCell: function (target, options) {
return target.each(function () {
endEditCell(this, options);
});
}
});
})(jQuery);