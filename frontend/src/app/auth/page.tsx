'use client';

import { UsuarioModel } from "@models/usuario.model";
import { api } from "@service/api";
import { Button, Card, Form, Input, message } from "antd";
import { useRouter } from "next/navigation";
import { useState } from "react";

const Auth = () => {
    const [loading, setLoading] = useState<boolean>(false);
    const [isRegister, setIsRegister] = useState<boolean>(false);
    const router = useRouter();

    const handleLogin = async (request: UsuarioModel): Promise<void> => {
        setLoading(true);
        try {
            const { data } = await api.post(`/${isRegister ? 'usuario/create' : 'auth/login'}`, request);

            message.success(isRegister ? 'Registrado com sucesso!' : 'Login realizado com sucesso!');
            if (!!data && data.accessToken) {
                localStorage.setItem('accessToken', data.accessToken);
            }

            router.push('/home');
        } catch (error: any) {
            message.error(error.response?.data?.message || 'Erro ao autenticar');
        }
        setLoading(false);
    }

    return (
        <div className="flex justify-center items-center min-h-screen bg-gray-100">
            <Card className="w-96 shadow-lg">
                <h2 className="text-center text-xl font-semibold">{isRegister ? "Registrar" : "Login"}</h2>
                <Form layout="vertical" onFinish={(request: UsuarioModel) => handleLogin(request)}>
                    <Form.Item
                        label="Email"
                        name="email"
                        rules={[{ required: true, type: "email", message: "Digite um email válido!" }]}
                    >
                        <Input placeholder="Digite seu email" />
                    </Form.Item>
                    <Form.Item
                        label="Senha"
                        name="senha"
                        rules={[{ required: true, min: 3, message: "A senha deve ter pelo menos 3 caracteres!" }]}
                    >
                        <Input.Password placeholder="Digite sua senha" />
                    </Form.Item>
                    <Button type="primary" htmlType="submit" block loading={loading}>
                        {isRegister ? "Registrar" : "Entrar"}
                    </Button>
                </Form>
                <p className="text-center mt-2">
                    {isRegister ? "Já tem uma conta?" : "Não tem uma conta?"}{" "}
                    <span
                        className="text-blue-500 cursor-pointer"
                        onClick={() => {
                            router.push('/home')
                            setIsRegister(!isRegister);
                        }}
                    >
                        {isRegister ? "Entrar" : "Registrar"}
                    </span>
                </p>
            </Card>
        </div>
    );
};

export default Auth;