package com.mincrm.min;

import com.mincrm.entity.model.MinAccount;
import com.mincrm.entity.model.MinVerification;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.entity.pojo.ShopVo;

import java.util.List;
import java.util.Map;

/**
 * Created by sunhh on 2019/12/15.
 */
public  interface IMin  {
    public MinAccountVo createMinAccount(MinAccountVo minAccountVo);
    public MinAccountVo loginOK(MinAccountVo minAccountVo);
    /**
     * 获取当前店铺的成员列表
     * @param currentShopVo
     */
    public Map<String, String> shopAccountMaps(ShopVo currentShopVo);
    public List<ShopVo> selectShopList(Integer uid);

    public void fuserupdate(MinAccountVo minAccountVo);

    public boolean fuserPasUpdate(MinAccountVo minAccountVo);
    public int saveVerification(MinVerification minVerification);
    public MinVerification getVerification(MinVerification  minVerification);
    public List<MinAccount> getMinAccount(MinAccountVo minAccountVo);
}
