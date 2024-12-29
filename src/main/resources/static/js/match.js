new Vue({
    el: '#app',
    data: {
        queryForm: {
            number: '',
            gender: '1'
        },
        activeTab: 'interested',
        queryResults: []
    },
    methods: {
        formatPhone(phone) {
            return phone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        },

        async switchTab(tab) {
            this.activeTab = tab;
            await this.queryUsers();
        },

        async queryUsers() {
            if (!this.queryForm.number) {
                alert('请输入用户编号');
                return;
            }

            try {
                let endpoint;
                switch(this.activeTab) {
                    case 'interested':
                        endpoint = 'viewInterestUsers';
                        break;
                    case 'interests':
                        endpoint = 'viewInterestInYou';
                        break;
                    case 'mutual':
                        endpoint = 'viewBothInterest';
                        break;
                }

                const response = await axios.post(`/pair/${endpoint}`, {
                    number: this.queryForm.number,
                    gender: parseInt(this.queryForm.gender)
                });
                this.queryResults = response.data;
            } catch (error) {
                console.error('查询失败:', error);
                alert('查询失败，请重试！');
            }
        }
    }
});