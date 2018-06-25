<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="goodsFileUploadDiv" style="display: none;">
	<form id="goodsFileUploadForm" class="form-horizontal layerForm" style="padding-left: 30px;">
		<div class="form-group">
		    <label for="exampleInputFile">文件上传</label>
		    <input type="file" id="fileInput">
	    </div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="isAutoCreateBrand" checked> 自动创建品牌
			</label>
		</div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="isAutoCreateCategory" checked> 自动创建分类
			</label>
		</div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="isAutoCreateUnit" checked> 自动创建单位
			</label>
		</div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="isExistUpdate" checked> 已存在商品更新
			</label>
		</div>
	</form>
</div>