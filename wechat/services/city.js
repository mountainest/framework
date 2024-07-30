import { config } from '../config/index';

function mockCity(code) {
  const { delay } = require('_utils/delay');
  const { getCityNameByCode } = require('../model/address');
  return getCityNameByCode(code);
}

export function fetchCityNameByCode(cityCode) {
  if (config.useMock) {
    return mockCity(cityCode);
  }
  return new Promise((resolve) => {
    resolve('real api');
  });
}
