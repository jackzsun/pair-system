new Vue({
    el: '#app',
    data: {
        userForm: {
            number: '',
            name: '',
            phone: '',
            gender: '1',
            interestNos: []
        },
        selectedInterests: []
    },
    methods: {
        padNumber(num) {
            return num.toString().padStart(2, '0');
        },

        validateForm() {
            if (!/^\d{2}$/.test(this.userForm.number)) {
                alert('编号必须是两位数字');
                return false;
            }
            if (!/^\d{11}$/.test(this.userForm.phone)) {
                alert('请输入正确的11位手机号');
                return false;
            }
            return true;
        },

        async saveUser() {
            if (!this.validateForm()) return;

            try {
                this.userForm.interestNos = this.selectedInterests;

                await axios.post('/pair/addUser', {
                    ...this.userForm,
                    gender: parseInt(this.userForm.gender)
                });

                alert('注册成功！');
                this.resetForm();
            } catch (error) {
                console.error('注册失败:', error);
                alert('注册失败，请重试！');
            }
        },

        resetForm() {
            this.userForm = {
                number: '',
                name: '',
                phone: '',
                gender: '1',
                interestNos: []
            };
            this.selectedInterests = [];
        }
    }
});