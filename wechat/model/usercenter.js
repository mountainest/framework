const userInfo = {
  avatarUrl:
    'https://we-retail-static-1300977798.cos.ap-guangzhou.myqcloud.com/retail-ui/components-exp/avatar/avatar-1.jpg',
  nickName: 'TDesign 🌟',
  phoneNumber: '13438358888',
  gender: 2,
  uid: 'suzhou',
  locationCode: '320500',
  birthday: 199001,
  height: 180,
  weight: 150,
  annualSalary: 100,
  companyType: 3,
  job: '会计',
  education: 3,
  province: 'jiangsu',
  city: 'nanjing',
  personality: '外向',
  hobbies: '游泳',
  constellation: '狮子座',
  addition: '无',
  pictures: '无',
  contact: '无',
  enabledFlg: true,
  coordinate: '',
};
const countsData = [
  {
    num: 2,
    name: '积分',
    type: 'point',
  },
  {
    num: 10,
    name: '优惠券',
    type: 'coupon',
  },
];

const orderTagInfos = [
  {
    orderNum: 1,
    tabType: 5,
  },
  {
    orderNum: 1,
    tabType: 10,
  },
  {
    orderNum: 1,
    tabType: 40,
  },
  {
    orderNum: 0,
    tabType: 0,
  },
];

const customerServiceInfo = {
  servicePhone: '4006336868',
  serviceTimeDuration: '每周三至周五 9:00-12:00  13:00-15:00',
};

export const genSimpleUserInfo = () => ({ ...userInfo });

export const genUsercenter = () => ({
  userInfo,
  countsData,
  orderTagInfos,
  customerServiceInfo,
});
