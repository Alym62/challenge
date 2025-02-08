'use client';

import { api } from '@service/api';
import { Button, message, Popconfirm } from 'antd';
import React from 'react';

interface DeleteContactConfirmProps {
    id: number | undefined;
    onDelete: () => void;
}

const DeleteContactConfirm: React.FC<DeleteContactConfirmProps> = ({ id, onDelete }) => {
    const handleDelete = async () => {
        try {
            await api.delete(`/contato/delete/${id}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
            });
            message.success('Contato removido com sucesso!');
            onDelete();
        } catch (error) {
            message.error('Erro ao remover contato');
        }
    };

    return (
        <Popconfirm
            title="Tem certeza que deseja excluir este contato?"
            onConfirm={handleDelete}
            okText="Sim"
            cancelText="Não"
        >
            <Button type="link" danger>
                Deletar
            </Button>
        </Popconfirm>
    );
};

export default DeleteContactConfirm;
