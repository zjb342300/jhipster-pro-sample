import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import axios from 'axios';

import * as config from '@/shared/config/config';
import ChangePassword from '@/account/change-password/change-password.vue';
import ChangePasswordClass from '@/account/change-password/change-password.component';

const localVue = createLocalVue();
const mockedAxios: any = axios;

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
}));

describe('ChangePassword Component', () => {
  let wrapper: Wrapper<ChangePasswordClass>;
  let changePassword: ChangePasswordClass;

  beforeEach(() => {
    mockedAxios.get.mockReset();
    mockedAxios.get.mockReturnValue(Promise.resolve({}));
    mockedAxios.post.mockReset();

    wrapper = shallowMount<ChangePasswordClass>(ChangePassword, {
      store,
      i18n,
      localVue,
    });
    changePassword = wrapper.vm;
  });

  it('should be a Vue instance', () => {
    expect(wrapper.isVueInstance()).toBeTruthy();
  });

  it('should show error if passwords do not match', () => {
    // GIVEN
    changePassword.resetPassword = { newPassword: 'password1', confirmPassword: 'password2' };
    // WHEN
    changePassword.changePassword();
    // THEN
    expect(changePassword.doNotMatch).toBe('ERROR');
    expect(changePassword.error).toBeNull();
    expect(changePassword.success).toBeNull();
  });

  it('should call Auth.changePassword when passwords match and  set success to OK upon success', async () => {
    // GIVEN
    changePassword.resetPassword = { currentPassword: 'password1', newPassword: 'password1', confirmPassword: 'password1' };
    mockedAxios.post.mockReturnValue(Promise.resolve({}));

    // WHEN
    changePassword.changePassword();
    await changePassword.$nextTick();

    // THEN
    expect(mockedAxios.post).toHaveBeenCalledWith('api/account/change-password', {
      currentPassword: 'password1',
      newPassword: 'password1',
    });

    expect(changePassword.doNotMatch).toBeNull();
    expect(changePassword.error).toBeNull();
    expect(changePassword.success).toBe('OK');
  });

  it('should notify of error if change password fails', async () => {
    // GIVEN
    changePassword.resetPassword = { currentPassword: 'password1', newPassword: 'password1', confirmPassword: 'password1' };
    mockedAxios.post.mockReturnValue(Promise.reject({}));

    // WHEN
    changePassword.changePassword();
    await changePassword.$nextTick();

    // THEN
    expect(changePassword.doNotMatch).toBeNull();
    expect(changePassword.success).toBeNull();
    expect(changePassword.error).toBe('ERROR');
  });
});
