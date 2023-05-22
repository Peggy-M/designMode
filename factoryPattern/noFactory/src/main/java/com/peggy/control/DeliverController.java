package com.peggy.control;

import com.alibaba.fastjson.JSON;
import com.peggy.service.SmallGiftInfo;
import com.peggy.dto.DiscountResult;
import com.peggy.response.AwardInfo;
import com.peggy.service.DiscountService;
import com.peggy.service.SmallGiftService;
import com.peggy.service.YouKuMemberService;

import java.util.UUID;

/**
 * @Author Peggy
 * @Date 2023-05-17 16:09
 **/
public class DeliverController {
    /**
     * 按照类型的不同发放商品
     * 奖品类型: 1 打折券 ,2 优酷会员,3 小礼品
     */
    public void awardToUser(AwardInfo awardInfo){

        if(awardInfo.getAwardTypes() == 1){ //打折券
            DiscountService discountService = new DiscountService();
            DiscountResult result = discountService.sendDiscount(awardInfo.getUid(),
                            awardInfo.getAwardNumber());
            System.out.println("打折券发放成功!"+ JSON.toJSON(result));
        }else if(awardInfo.getAwardTypes() == 2){ //优酷会员
            //获取用户手机号
            String bindMobile = awardInfo.getExtMap().get("phone");
            //调用service
            YouKuMemberService youKuMemberService = new YouKuMemberService();
            youKuMemberService.openMember(bindMobile,awardInfo.getAwardNumber());
            System.out.println("优酷会员发放成功!");
        }else if(awardInfo.getAwardTypes() == 3){
            /*小礼品 封装收货用户信息 */
            SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
            smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
                    smallGiftInfo.setOrderId(UUID.randomUUID().toString());
            smallGiftInfo.setRelAddress(awardInfo.getExtMap().get("adderss"));
                    SmallGiftService smallGiftService = new SmallGiftService();
            Boolean isSuccess = smallGiftService.giveSmallGift(smallGiftInfo);
            System.out.println("小礼品发放成功!" + isSuccess);
        }

    }
}
