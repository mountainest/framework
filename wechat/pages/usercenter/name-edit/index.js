Page({
  data: {
    nameValue: '',
  },
  onLoad(options) {
    const { name } = options;
    this.setData({
      nameValue: name,
    });
  },
  onSubmit() {
    let pages = getCurrentPages();
    let prePage = pages[pages.length - 2];
    let value = this.data.nameValue;
    wx.navigateBack({
      delta: 1,
      success: function(e) {
        prePage.callback(value);
      },
    });
  },
  clearContent() {
    this.setData({
      nameValue: '',
    });
  },
});
