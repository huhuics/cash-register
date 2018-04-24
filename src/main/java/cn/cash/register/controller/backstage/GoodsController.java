package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsQuantityUnit;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsQuantityUnitService;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理商品相关Controller
 * @author 51
 * @version $Id: GoodsController.java, v 0.1 2018年4月18日 下午4:24:57 51 Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    private static final Logger      logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsInfoService         goodsInfoService;

    @Resource
    private GoodsQuantityUnitService unitService;

    /**
     * 跳转到商品资料页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_goods-list";
    }

    /**
     * 查询商品资料列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsInfoList")
    public ResultSet queryGoodsInfoList(GoodsInfoQueryRequest request) {
        PageInfo<GoodsInfo> queryList = goodsInfoService.queryList(request);
        return ResultSet.success().put("page", queryList);
    }

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
    @RequestMapping(value = "/queryAllGoodsUnit")
    public ResultSet queryAllGoodsUnit() {
        List<GoodsQuantityUnit> units = unitService.queryAll();
        return ResultSet.success().put("units", units);
    }

    /*******************************end****************************/

}
