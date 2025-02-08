'use client';

import { ContatoModel } from '@models/contato.model';
import { api } from '@service/api';
import { Button, Form, Input, message, Modal } from 'antd';
import React from 'react';

interface EditContactModalProps {
    visible: boolean;
    contact: ContatoModel | null;
    onClose: () => void;
    onEdit: (contact: ContatoModel) => void;
}

const EditContactModal: React.FC<EditContactModalProps> = ({ visible, contact, onClose, onEdit }) => {
    const [form] = Form.useForm();

    const handleEdit = async (values: ContatoModel) => {
        if (contact) {
            try {
                await api.put(`/contato/update/${contact.id}`, values, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                    },
                });
                message.success('Contato editado com sucesso!');
                onEdit(values);
                onClose();
            } catch (error) {
                message.error('Erro ao editar contato');
            }
        }
    };

    return (
        <Modal
            title="Editar Contato"
            visible={visible}
            onCancel={onClose}
            footer={null}
        >
            <Form
                form={form}
                onFinish={handleEdit}
                layout="vertical"
            >
                <Form.Item
                    name="nome"
                    label="Nome"
                    rules={[{ required: true, message: 'Por favor, insira o nome!' }]}
                    initialValue={contact?.nome}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="sobrenome"
                    label="Sobrenome"
                    rules={[{ required: true, message: 'Por favor, insira o Sobrenome!' }]}
                    initialValue={contact?.sobrenome}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="email"
                    label="Email"
                    rules={[{ required: true, message: 'Por favor, insira o email!' }]}
                    initialValue={contact?.email}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="telefone"
                    label="Telefone"
                    rules={[{ required: true, message: 'Por favor, insira o telefone!' }]}
                    initialValue={contact?.telefone}
                >
                    <Input />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Editar
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    );
};

export default EditContactModal;
