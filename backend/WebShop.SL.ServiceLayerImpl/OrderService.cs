using System;
using System.Collections.Generic;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;

namespace WebShop.SL.ServiceLayerImpl
{
    class OrderService : IOrderService
    {
        private IUnitOfWork _uow;

        public OrderService(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public void Create(OrderDTO order)
        {
            _uow.OrderOperations.Create(order);
            _uow.SaveChanges();
        }

        public IEnumerable<OrderDTO> GetAll()
        {
            return _uow.OrderOperations.Get();
        }
    }
}
