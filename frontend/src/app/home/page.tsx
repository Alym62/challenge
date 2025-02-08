'use client';

import AddContactModal from "@components/add.modal";
import DeleteContactConfirm from "@components/confirm.modal";
import EditContactModal from "@components/edit.modal";
import { useAuth } from "@hooks/useAuth";
import { usePaginatedData } from "@hooks/usePagineted";
import { ContatoModel } from "@models/contato.model";
import { Button, Form, Input, message, Table } from "antd";
import { useRouter } from "next/navigation";
import { useState } from "react";

const Home = () => {
    const { isAuthenticated } = useAuth();
    const { data, loading, page, setPage, total, pageSizeState, setPageSizeState, refetch } = usePaginatedData('/contato/pager');
    const router = useRouter();

    const [isAddModalVisible, setIsAddModalVisible] = useState(false);
    const [isEditModalVisible, setIsEditModalVisible] = useState(false);
    const [editingContact, setEditingContact] = useState<ContatoModel | null>(null);
    const [filters, setFilters] = useState({
        nome: '',
        email: '',
    });

    const showAddModal = () => setIsAddModalVisible(true);
    const closeAddModal = () => setIsAddModalVisible(false);

    const showEditModal = (contact: ContatoModel) => {
        setEditingContact(contact);
        setIsEditModalVisible(true);
    };

    const closeEditModal = () => setIsEditModalVisible(false);

    const handleEdit = (updatedContact: ContatoModel) => {
        const updatedData = data.map((contact) =>
            contact.id === updatedContact.id ? updatedContact : contact
        );
        setEditingContact(null);
    };

    const handleAdd = (newContact: ContatoModel) => {
        refetch();
        message.success('Contato adicionado com sucesso!');
    };

    const handleDelete = (deletedId: number | undefined) => {
        refetch();
        message.success('Contato removido com sucesso!');
    };

    const handleFilterChange = (value: string, field: string) => {
        setFilters({
            ...filters,
            [field]: value,
        });
    };

    const handleFilterSubmit = () => {
        refetch(filters);
    };

    if (!isAuthenticated) {
        return null;
    }

    const columns = [
        {
            title: 'ID', dataIndex: "id", key: "id",
        },
        {
            title: 'Nome', dataIndex: "nome", key: "nome",
        },
        {
            title: 'Email', dataIndex: "email", key: "email",
        },
        {
            title: 'Telefone', dataIndex: "telefone", key: "telefone",
        },
        {
            title: 'Data de criação', dataIndex: "dataCriacao", key: "dataCriacao",
        },
        {
            title: 'Ações',
            key: 'actions',
            render: (_: any, record: ContatoModel) => (
                <>
                    <Button onClick={() => showEditModal(record)} type="link">Editar</Button>
                    <DeleteContactConfirm id={record.id} onDelete={() => handleDelete(record.id)} />
                </>
            ),
        },
    ];

    return (
        <section className="p-3">
            <nav className="flex justify-between">
                <div className="mb-4">
                    <Form layout="inline" onFinish={handleFilterSubmit}>
                        <Form.Item label="Nome">
                            <Input
                                value={filters.nome}
                                onChange={(e) => handleFilterChange(e.target.value, 'nome')}
                                placeholder="Filtrar por nome"
                            />
                        </Form.Item>
                        <Form.Item label="Email">
                            <Input
                                value={filters.email}
                                onChange={(e) => handleFilterChange(e.target.value, 'email')}
                                placeholder="Filtrar por email"
                            />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit">Filtrar</Button>
                        </Form.Item>
                    </Form>
                </div>
                <Button type="primary" onClick={showAddModal} style={{ marginBottom: 16 }}>
                    Adicionar Contato
                </Button>
            </nav>
            <Table
                columns={columns}
                dataSource={data}
                rowKey="id"
                pagination={{
                    current: page + 1,
                    total,
                    pageSize: pageSizeState,
                    onChange(newPage) {
                        setPage(newPage - 1);
                    },
                    onShowSizeChange(current, size) {
                        setPageSizeState(size);
                    },
                }}
            />
            <AddContactModal visible={isAddModalVisible} onClose={closeAddModal} onAdd={handleAdd} />
            <EditContactModal
                visible={isEditModalVisible}
                contact={editingContact}
                onClose={closeEditModal}
                onEdit={handleEdit}
            />
            <Button onClick={() => {
                localStorage.removeItem('accessToken');
                router.push('/auth');
            }}>Logout</Button>
        </section>
    );
};

export default Home;
