const Mock = require('mockjs')

const carousel = Mock.mock({
  items: [
    {
      id: 0,
      src: 'https://p3.lefile.cn/fes/cms/2021/11/15/lfomj18t0ozui06aom0omx4gcslfqh692967.jpg'
    },
    {
      id: 1,
      src: 'https://p3.lefile.cn/fes/cms/2021/11/15/oat8obgf99qdck8j3hdf1hrs8815kw734497.jpg'
    },
    {
      id: 2,
      src: 'https://p1.lefile.cn/fes/cms/2021/11/15/kl8sdwjxe7ua44h1hnv1sdbqzugymr864769.jpg'
    },
    {
      id: 3,
      src: 'https://p3.lefile.cn/fes/cms/2021/11/16/yotb4twv5qayu42ea352bcmbu7hpsn389856.jpg'
    },
    {
      id: 4,
      src: 'https://p2.lefile.cn/fes/cms/2021/11/15/0b7fuo9xxitsnq11esk5zwa63ckefy798333.jpg'
    }
  ]
})

const aboutus_data = Mock.mock({
  items: [
    '浩旭物流是2013年由美国硅谷投资，在英国注册的跨境物流公司。主营核心服务是欧洲至中国的国际快递，海淘转运和大宗货物空运。自成立以来，我们已服务英国及欧洲的数百万客户，覆盖留学生，海淘买手以及跨境电商。我们每年承运的包裹数量百万件， 包含各类个人物品，文件， 海淘商品，归国行李等等。',
    '浩旭物流业务范围覆盖英国，德国，爱尔兰，意大利，法国和西班牙等多个欧洲国家。我们不仅有重点城市的线下实体店， 完备的自营取件车队， 还有多个现代化运营中心， 数千个第三方合作的投递点。我们可在中国多个清关口岸办理清关，为我们的客户提供最快速的门到门跨境体验。浩旭物流与国际巨头英国皇家邮政Roymail, UPS, DHL, bpost, DPD 等合作多年，并在2017，成为英国唯一的阿里巴巴旗下菜鸟物流的官方指定合作物流商，为各类淘宝天猫海外商家提供助力！',
    '浩旭物流不忘初心，我们坚持提供最稳定，最负责，最安心的服务，我们愿与您一路同行，是您最佳的物流选择！'
  ]
})

const news_data = Mock.mock({
  items: [
    {
      id: 100001,
      title: '哪些物品禁运，后果是什么？',
      timestamp: '@datetime',
      views: '@integer(100, 5000)',
      pic: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2018-07-17%2Ffdf563ab1b116fe8613d859851b200fc%2F00001.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639992703&t=f851f9c3ddae2baa5dc96fb66fe2085e',
      content:
        '常见的禁运的物品有喷雾剂瓶,例如Body Spray, 脱毛膏，含酒精的液体，指甲油，珠宝现金,香水，口红，喷雾剂，普通液体每单液体不超过1L,等易爆物品，如烟火制品（礼花、鞭炮、摔炮、拉炮等）发令纸、干冰、打火机、煤气炉、煤气罐；（特别注意，任何液压的有内压的物品，类似于喷雾类杀虫剂，空气清新剂，发型固定喷雾等都属于此类）易燃液体，如汽油、煤油、柴油、苯、酒精、油漆、油墨、去光水等；（特别注意，指甲油属于此类） 易燃固体、自燃物品和遇湿易燃物品，如煤、松香、石蜡、活性碳、白磷、黄磷、油麻、金属钠、镁铝粉等；氧化剂和有机过氧化物，如氧化氢、过氧化钠、次氯酸钙、氯酸钾、硝酸钾、不明化学成份物品等；毒害品和感染性物品。指在流通过程中容易发生中毒和感染的物品，如杀虫剂、灭草剂、砷及其化合物、汞及其化合物、氰及其化合物、哥罗仿、苯铵、生漆、血液、尿液、体液、炭疽、危险性病菌等； 腐蚀品，能灼烧人体组织，并对金属等物品造成损坏的固体和液体，如硫酸、硝酸、盐酸、氯磺酸、冰醋酸、烧碱、双氧水等如果包裹有禁运物品，该包裹可能会被运营商提货到仓库后直接退回发件人手中，我们会帮您向运营商申请包裹的退款，但是要扣除50英镑的手续费。如果有违禁物品的包裹丢失，我们对此不会做任何丢失的理赔。 所以请务必搞清楚您所邮寄的物品是否属于禁运品。'
    },
    {
      id: 100002,
      title: '罐奶粉包税线路每个收件人/每个地址可以邮寄多少箱',
      timestamp: '@datetime',
      views: '@integer(100, 5000)',
      pic: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.qttcwf.tw%2Fupload%2Fimage%2F201901%2F5c4a73400c1f1.jpg&refer=http%3A%2F%2Fwww.qttcwf.tw&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639992787&t=3dcca1de12d970e99940270cef07cb1f',
      content:
        '奶粉包税路线同一个身份证/收件人电话/地址，每一天最多下2箱，第二天又可以继续下单。如您想邮寄多箱到同一地址，您可以使用不同收件人姓名，身份证，电话号码。例如：孩子的妈妈，爸爸，爷爷，奶奶，4个人，每个人的姓名，身份证，电话号，分别作为收件人，这样同一个地址，一天一共可以邮寄8箱'
    },
    {
      id: 100003,
      title: '奶粉包税路线能不能夹带物品',
      timestamp: '@datetime',
      views: '@integer(100, 5000)',
      pic: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F34034620%2F20160415160637_53829.jpg&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639992905&t=df946337b37535f4c1391d3b0369f340',
      content:
        '不能！英卖通6罐/4罐奶粉专线不能夹带奶粉以外的物品。一经发现夹带，夹带物将被销毁，且不赔付任何因夹带造成的包裹延误，丢失，销毁。客户需另外支付10英镑罚金。如夹带物品在到达国内时被海关部门查获，夹带物品将连同奶粉就地销毁，我司不承担任何责任，无赔偿。如需奶粉夹带物品，请用Bpost/比利时邮政，parcelforce/英国邮政，只需如实填写重量及箱子长宽高即可，没有其他限制。'
    },
    {
      id: 100004,
      title: '包裹几天到达国内？时效如何？',
      timestamp: '@datetime',
      views: '@integer(100, 5000)',
      pic: 'https://www.shenzhou.co.uk//Upload/wz/news/9e62197a-e65f-4dda-9be8-2d6ecad980b4.jpg',
      content:
        '不同专线，时效不同，碰上节假日及海关抽查也会有一定延迟，请综合以上情况选择最合适的线路下单。（以下时效不包含海关清关时效）3000小包杂货包税路线：7-10天左右<p>英卖通4/6罐奶粉包税路线：7-10天左右<p>英邮宝333包税路线：7-12天左右<p>英邮宝1500包税路线：7-15天左右<p>英邮宝3000包税路线：7-15天左右<p>轻奢&重奢包税路线：7-15天左右<p>Bpost/比利时邮政：7-14天左右<p>parcel force/ 全球优先：3-7天左右<p>parcel force/邮政经济包/安全座椅/行李专线/小包裹专线：7-14天左右'
    }
  ]
})

const problem_data = Mock.mock({
  items: [
    {
      question: '如何付款',
      answer:
        '在线充值：支持Visa，支付宝， Mastercard，Maestro等多种方式的在线充值方式。最低一次充值￡50, 最高充值 ￡2000. 非英镑币种充值，当日即时汇率以速递中国官方网站显示为准。您还可以通过银行转账方式，直接付款至速递中国英国公司账户。'
    },
    {
      question: '如何打包',
      answer:
        '您可到我们的实体店打包，或您在家自己打包好。如果是邮寄奶粉，我们建议客户使用双层(Double Wall)的纸箱，并在包裹内部做好足够的减震措施（如缠绕气泡膜；奶粉建议使用气柱袋等），防止包裹内的物品在长途运输过程中损坏。'
    },
    {
      question: '包裹几天到达国内？时效如何？',
      answer:
        '不同专线，时效不同，碰上节假日及海关抽查也会有一定延迟，请综合以上情况选择最合适的线路下单。（以下时效不包含海关清关时效）3000小包杂货包税路线：7-10天左右'
    },
    {
      question: '如何开通VIP大客户“批量发货”？',
      answer:
        '在下单页面，批量发货 需联系我们的VIP大客户经理开通，此线路需达到每次30箱以上发货量。价格全英匹配最低价。如果您需要开通批量发货服务，请您拨打客服01293 537 035，转接大客户经理。'
    },
    {
      question: '如何快速下单 客服帮助下单吗？',
      answer:
        '您可通过我们的网站自助下单， 我们的客服不能帮助下单网站下单的基本流程为： 1. 网站注册   >  2. 快速询价  >  3. 选择您需要的服务产品  >  4. 填写订单信息表  >  5. 在线付款  >  6. 打印面单发走包裹'
    }
  ]
})
module.exports = [
  {
    url: '/logistic-test-api/getAbout',
    type: 'get',
    response: (config) => {
      const items = aboutus_data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/logistic-test-api/getNews',
    type: 'get',
    response: (config) => {
      const items = news_data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/logistic-test-api/getQuestion',
    type: 'get',
    response: (config) => {
      const items = problem_data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/logistic-test-api/carousel',
    type: 'get',
    response: (config) => {
      const items = carousel.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
