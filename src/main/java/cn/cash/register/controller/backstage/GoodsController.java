package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsBrand;
import cn.cash.register.dao.domain.GoodsCategory;
import cn.cash.register.dao.domain.GoodsColor;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsQuantityUnit;
import cn.cash.register.dao.domain.GoodsSize;
import cn.cash.register.dao.domain.GoodsTag;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.service.GoodsBrandService;
import cn.cash.register.service.GoodsCategoryService;
import cn.cash.register.service.GoodsColorService;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsQuantityUnitService;
import cn.cash.register.service.GoodsSizeService;
import cn.cash.register.service.GoodsTagService;
import cn.cash.register.util.NumUtil;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理商品相关Controller
 * @author 51
 * @version $Id: GoodsController.java, v 0.1 2018年4月18日 下午4:24:57 51 Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Resource
    private GoodsInfoService         goodsInfoService;

    @Resource
    private GoodsQuantityUnitService unitService;

    @Resource
    private GoodsTagService          tagService;

    @Resource
    private GoodsSizeService         sizeService;

    @Resource
    private GoodsColorService        colorService;

    @Resource
    private GoodsBrandService        brandService;

    @Resource
    private GoodsCategoryService     categoryService;

    /**
     * 跳转到商品资料页
     */
    @GetMapping
    public String list() {
        return "backstage/_goods-list";
    }

    /*****************************商品信息相关**************************/
    /**
     * 查询商品资料列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsInfoList")
    public ResultSet queryGoodsInfoList(GoodsInfoQueryRequest request) {
        PageInfo<GoodsInfo> queryList = goodsInfoService.queryList(request);
        return ResultSet.success().put("page", queryList);
    }

    /**
     * 增加商品信息
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsInfo")
    public ResultSet addGoodsInfo(GoodsInfoRequest request) {
        Long id = goodsInfoService.add(request);
        return ResultSet.success().put("id", id);
    }

    /**
     * 修改商品信息
     * 1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsInfo")
    public ResultSet updateGoodsInfo(GoodsInfoRequest request) {
        int result = goodsInfoService.update(request);
        return ResultSet.success().put("result", result);
    }

    /**
     * 删除商品信息
     * 可删除单个商品,也支持批量删除
     * @param ids  商品主键id
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsInfo")
    public void deleteGoodsInfo(List<Long> ids) {
        goodsInfoService.delete(ids);
    }

    /**
     * 根据商品id查询商品详情
     * @param goodsInfoId  商品id
     * @return             商品信息对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsInfoById")
    public ResultSet queryGoodsInfoById(Long goodsInfoId) {
        GoodsInfo goodsInfo = goodsInfoService.queryById(goodsInfoId);
        return ResultSet.success().put("goodsInfo", goodsInfo);
    }

    /**
     * 根据关键字搜索商品
     * @param keyword 条码/拼音码/商品名
     * @return  商品信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchGoodsInfo")
    public ResultSet searchGoodsInfo(String keyword) {
        List<GoodsInfo> goodsInfos = goodsInfoService.search(keyword);
        return ResultSet.success().put("goodsInfos", goodsInfos);
    }

    /**
     * 批量修改操作
     * @param goodsIds   商品id集合
     * @param newValue   被修改字段的新的值
     * @param filedEnum  被修改的字段枚举{@link UpdateFieldEnum},只有枚举中的字段才支持批量修改
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpdate")
    public void batchUpdate(List<Long> goodsIds, Object newValue, UpdateFieldEnum filedEnum) {
        goodsInfoService.batchUpdate(goodsIds, newValue, filedEnum);
    }

    /**
     * 获取商品条码
     */
    @ResponseBody
    @GetMapping(value = "/getBarCode")
    public ResultSet getBarCode() {
        return ResultSet.success().put("barCode", NumUtil.getBarCode());
    }

    /**
     * 获取商品图片
     * @param goodsImageId 商品图片id
     * @return 图片对象
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsImage")
    public ResultSet getGoodsImage(Long goodsImageId) {
        GoodsImage image = goodsInfoService.queryGoodsImage(goodsImageId);
        return ResultSet.success().put("image", image);
    }

    /**
     * 商品图片的增、删、改都可以调用此方法,删除的时候图片数组传null
     * @param goodsInfoId  商品id
     * @param goodsImage   图片二进制数据
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsImage")
    public void updateGoodsImage(Long goodsInfoId, byte[] goodsImage) {
        goodsInfoService.updateGoodsImage(goodsInfoId, goodsImage);
    }

    /*******************************end****************************/

    /*****************************商品种类相关**************************/
    /**
     * 增加商品种类
     * @param category 需填充categoryName和parentId两个字段
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsCategory")
    public ResultSet addGoodsCategory(GoodsCategory category) {
        Long id = categoryService.add(category);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品种类
     * 该删除方法会将该结点及其子孙结点都删除
     * @param id 商品种类主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsCategory")
    public ResultSet deleteGoodsCategory(Long id) {
        categoryService.delete(id);
        return ResultSet.success().put("result", 1);
    }

    /**
     * 修改商品种类
     * @param category  需填充对象的id和categoryName两个值
     * @return          1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsCategory")
    public ResultSet updateGoodsCategory(GoodsCategory category) {
        int result = categoryService.update(category);
        return ResultSet.success().put("result", result);
    }

    /**
     * 获取商品种类树
     * @param categoryId 当查询整棵树时填1,即根节点id
     * @return json数组
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsCategoryTree")
    public ResultSet getGoodsCategoryTree(Long categoryId) {
        JSONArray tree = categoryService.getTree(categoryId);
        return ResultSet.success().put("tree", tree);
    }

    /*******************************end****************************/

    /*****************************商品品牌相关**************************/
    /**
     * 增加商品品牌
     * @param brandName 商品品牌名称
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsBrand")
    public ResultSet addGoodsBrand(String brandName) {
        Long id = brandService.addBrand(brandName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品品牌
     * @param id 商品品牌主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsBrand")
    public ResultSet deleteGoodsBrand(Long id) {
        int result = brandService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品品牌
     * @param brand  只需填充对象的id和brandName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsBrand")
    public ResultSet updateGoodsBrand(GoodsBrand brand) {
        int result = brandService.update(brand);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品品牌
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsBrand")
    public ResultSet queryAllGoodsBrand() {
        List<GoodsBrand> brands = brandService.queryAll();
        return ResultSet.success().put("brands", brands);
    }

    /*******************************end****************************/

    /*****************************商品颜色相关**************************/
    /**
     * 增加商品颜色
     * @param colorName 颜色名称
     * @return 主键
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsColor")
    public ResultSet addGoodsColor(String colorName) {
        Long id = colorService.addColor(colorName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品颜色
     * @param id 商品颜色主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsColor")
    public ResultSet deleteGoodsColor(Long id) {
        int result = colorService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品颜色
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsColor")
    public ResultSet queryAllGoodsColor() {
        List<GoodsColor> colors = colorService.queryAll();
        return ResultSet.success().put("colors", colors);
    }

    /*******************************end****************************/

    /*****************************商品尺寸相关**************************/
    /**
     * 增加商品尺寸
     * @param sizeName 尺寸名称,如L,M,S
     * @return 主键
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsSize")
    public ResultSet addGoodsSize(String sizeName) {
        Long id = sizeService.addSize(sizeName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品尺寸
     * @param id 商品尺寸主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsSize")
    public ResultSet deleteGoodsSize(Long id) {
        int result = sizeService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品尺寸
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsSize")
    public ResultSet queryAllGoodsSize() {
        List<GoodsSize> sizes = sizeService.queryAll();
        return ResultSet.success().put("sizes", sizes);
    }

    /*******************************end****************************/

    /*****************************商品标签相关**************************/
    /**
     * 增加商品标签
     * @param tagName 商品标签名称
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsTag")
    public ResultSet addGoodsTag(String tagName) {
        Long id = tagService.add(tagName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品标签
     * @param id 商品标签主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsTag")
    public ResultSet deleteGoodsTag(Long id) {
        int result = tagService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品标签
     * @param tag  只需填充对象的id和tagName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsTag")
    public ResultSet updateGoodsTag(GoodsTag tag) {
        int result = tagService.update(tag);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据主键查询商品标签
     * @param id  商品标签主键id
     * @return    商品标签对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsTagById")
    public ResultSet queryGoodsTagById(Long id) {
        GoodsTag tag = tagService.queryById(id);
        return ResultSet.success().put("tag", tag);
    }

    /**
     * 查询所有商品标签
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsTag")
    public ResultSet queryAllGoodsTag() {
        List<GoodsTag> tags = tagService.queryAll();
        return ResultSet.success().put("tags", tags);
    }

    /*******************************end****************************/

    /*****************************商品单位相关**************************/
    /**
     * 增加商品单位
     * @param unitName 商品单位名称,如件、个
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsUnit")
    public ResultSet addGoodsUnit(String unitName) {
        Long id = unitService.add(unitName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品单位
     * @param id 商品单位主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsUnit")
    public ResultSet deleteGoodsUnit(Long id) {
        int result = unitService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品单位
     * @param unit 只需填充对象的id和unitName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsUnit")
    public ResultSet updateGoodsUnit(GoodsQuantityUnit unit) {
        int result = unitService.update(unit);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据主键查询商品单位
     * @param id  商品单位主键id
     * @return    商品单位对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsUnitById")
    public ResultSet queryGoodsUnitById(Long id) {
        GoodsQuantityUnit unit = unitService.queryById(id);
        return ResultSet.success().put("unit", unit);
    }

    /**
     * 查询所有商品单位
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsUnit")
    public ResultSet queryAllGoodsUnit() {
        List<GoodsQuantityUnit> units = unitService.queryAll();
        return ResultSet.success().put("units", units);
    }

    /*******************************end****************************/

}
