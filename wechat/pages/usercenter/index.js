import { fetchPerson } from '../../services/usercenter/fetchPerson';
import { phoneEncryption } from '../../utils/util';
// import Toast from 'tdesign-miniprogram/toast/index';

Page({
  data: {
    personInfo: {
      avatarUrl: '',
      nickName: '正在登录...',
      gender: 0,
      phoneNumber: '',
    },
    currAuthStep: 1,
    showUnbindConfirm: false,
    pickerOptions: [
      {
        name: '男',
        code: '1',
      },
      {
        name: '女',
        code: '2',
      },
    ],
    typeVisible: false,
    genderMap: ['', '男', '女'],
  },

  onLoad() {
    this.init();
  },

  onShow() {
    this.getTabBar().init();
    this.init();
  },
  onPullDownRefresh() {
    this.init();
  },

  init() {
    this.fetchData();
  },

  fetchData() {
    fetchPerson().then((personInfo) => {
      this.setData({
        personInfo,
        'personInfo.phoneNumber': phoneEncryption(personInfo.phoneNumber),
        currAuthStep: 2,
      });
      wx.stopPullDownRefresh();
    });
  },

  gotoUserEditPage() {
    const { currAuthStep } = this.data;
    if (currAuthStep === 2) {
      wx.navigateTo({ url: '/pages/usercenter/person-info/index' });
    } else {
      this.fetchData();
    }
  },

});
