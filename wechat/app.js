import updateManager from './common/updateManager';

App({
  onLaunch: function () {
    this.login();
  },
  login: function() {
    wx.login({
      success: (res) => {
        // code = res.code;
        console.log("登录成功。", res.errMsg);
      },
      fail: (res) => {
        console.log("登录失败。", res.errMsg);
      }
    })
  },
  onShow: function () {
    updateManager();
  },
});
