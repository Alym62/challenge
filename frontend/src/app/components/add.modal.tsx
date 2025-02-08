'use client';

import { ContatoModel } from '@models/contato.model';
import { api } from '@service/api';
import { Button, Form, Input, message, Modal } from 'antd';
import React from 'react';

interface AddContactModalProps {
    visible: boolean;
    onClose: () => void;
    onAdd: (contact: ContatoModel) => void;
}

const AddContactModal: React.FC<AddContactModalProps> = ({ visible, onClose, onAdd }) => {
    const [form] = Form.useForm();

    const handleAdd = async (values: ContatoModel) => {
        try {
            await api.post('/contato/create', values, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
            });
            message.success('Contato adicionado com sucesso!');
            onAdd(values);
            onClose();
        } catch (error) {
            message.error('Erro ao adicionar contato');
        }
    };

    return (
        <Modal
            title="Adicionar Contato"
            visible={visible}
            onCancel={onClose}
            footer={null}
        >
            <Form
                form={form}
                onFinish={handleAdd}
                layout="vertical"
            >
                <Form.Item
                    name="nome"
                    label="Nome"
                    rules={[{ required: true, message: 'Por favor, insira o nome!' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="sobrenome"
                    label="Sobrenome"
                    rules={[{ required: true, message: 'Por favor, insira o sobrenome!' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="email"
                    label="Email"
                    rules={[{ required: true, message: 'Por favor, insira o email!' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="telefone"
                    label="Telefone"
                    rules={[{ required: true, message: 'Por favor, insira o telefone!' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Adicionar
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    );
};

export default AddContactModal;
