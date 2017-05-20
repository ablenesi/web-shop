using System.Collections.Generic;
using System.Linq;

using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.BL.BusinessLayerImpl
{
    public class OrderOperations : IOrderOperations
    {
        private IDataAccess<Order> _orderDataAccess;
        private IDataAccess<OrderDetail> _orderDetailDataAccess;

        public OrderOperations(IDataAccess<Order> orderDataAccess,
                               IDataAccess<OrderDetail> orderDetailDataAccess)
        {
            _orderDataAccess = orderDataAccess;
            _orderDetailDataAccess = orderDetailDataAccess;
        }

        public void Create(OrderDTO order)
        {
            _orderDataAccess.Add(new Order
            {
                Name = order.Name,
                Address = order.Address,
                Date = order.Date,
                OrderDetails = order.OrderDetails.Select(x => Create(x)).ToArray()
            });
        }

        public IEnumerable<OrderDTO> Get()
        {
            return _orderDataAccess.Read()
                .Select(x => new OrderDTO {
                    Id = x.Id,
                    Name = x.Name,
                    Address = x.Address,
                    Date = x.Date,
                    OrderDetails = x.OrderDetails.Select(orderDetail => new OrderDetailDTO()
                    {
                        ProductId = orderDetail.ProductId,
                        OrderId = orderDetail.OrderId,
                        Quantity = orderDetail.Quantity
                    }).ToArray()
                });
        }

        #region OrederDetail
        private OrderDetail Create(OrderDetailDTO orderDetail)
        {
            var _orderDetail = new OrderDetail
            {
                ProductId = orderDetail.ProductId,
                OrderId = orderDetail.OrderId,
                Quantity = orderDetail.Quantity
            };
            _orderDetailDataAccess.Add(_orderDetail);
            return _orderDetail;
        }
        #endregion
    }
}
