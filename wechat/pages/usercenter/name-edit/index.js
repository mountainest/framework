Page({
  data: {
    key: '',
    value: '',
  },
  onLoad(options) {
    const { key, value } = options;
    this.setData({
      key: key,
      value: value,
    });
  },
  onSubmit() {
    let pages = getCurrentPages();
    let prePage = pages[pages.length - 2];
    let key = this.data.key;
    let value = this.data.value;
    wx.navigateBack({
      delta: 1,
      success: function(e) {
        prePage.callback(key, value);
      },
    });
  },
  clearContent() {
    this.setData({
      value: '',
    });
  },
});
