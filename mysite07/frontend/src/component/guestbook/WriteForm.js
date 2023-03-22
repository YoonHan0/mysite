import React, {useRef} from 'react';
import styles from '../../assets/scss/component/guestbook/WriteForm.scss';

export default function WriteForm({notifyMessage}) {
    const refForm = useRef(null);

    const handleSubmit = function (e) {
        console.log(e);
        console.log(e.target);
        e.preventDefault();
        try {
            /* input이라는 Parmeter로 e.target애 하나씩 접근 */
            const newMessage = Array.from(e.target, (input) => {
                    // simple validatation of empty string
                    if (input.value === '') {
                        throw `validation ${input.placeholder} is empty ''`;
                    }
                    return {n: input.name, v: input.value};
                })
                .filter(({n}) => n !== '')
                .reduce((res, {n, v}) => {
                    console.log(`res: ${res}, n: ${n}, v: ${v}`);
                    res[n] = v;
                    return res;
                }, {});
            console.log(`newMessage: ${newMessage}`);
            refForm.current.reset();
            notifyMessage.add(newMessage);
        } catch (err) {
            console.error(err);
        }
    }


    return (
        <form
            ref={refForm}
            onSubmit={handleSubmit}
            className={styles.WriteForm}>
            <input
                type={'text'}
                name={'name'}
                placeholder={'이름'}
                autoComplete={' off'}/>
            <input
                type={'password'}
                name={'password'}
                placeholder={'비밀번호'}
                autoComplete={'off'}/>
            <textarea
                name={'message'}
                placeholder={'메세지(내용)'}/>
            <input
                type={'submit'}
                value={'보내기'}
                autoComplete={'off'}/>
        </form>
    );
}