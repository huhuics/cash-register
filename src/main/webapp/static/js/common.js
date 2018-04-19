function getRootPath_web() {
	var curWwwPath = window.document.location.href;	// 当前完整网址
	var pathName = window.document.location.pathname; // 主机地址之后的目录
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos); // 主机地址，如： http://localhost:8080
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1); // 带"/"的项目名
	return (localhostPaht + projectName);
}

var basePath = getRootPath_web();